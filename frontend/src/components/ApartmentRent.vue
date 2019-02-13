<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartment rent</h1></div>
        <div>
            <b-row>
                <b-col>
                    <b-alert :show="serviceErrors.length <= 0 && apartmentRentSuccess !== null" variant="success">
                        <h4>Booking action successful</h4>
                        <hr>
                        <p class="mb-0">{{ apartmentRentSuccess }}</p>
                    </b-alert>
                    <b-alert :show="serviceErrors.length > 0"
                             v-for="error in serviceErrors" :key="error"
                             variant="danger">
                        <h4>Could not update apartment!</h4>
                        <hr>
                        <p class="mb-0">{{ error }}</p>
                    </b-alert>
                </b-col>
            </b-row>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                <b-form-group id="apartmentIdInputGroup"
                              label="Id:"
                              label-for="apartmentId">
                    <b-form-input id="apartmentIdInput"
                                  type="text"
                                  v-model="apartment.apartmentId"
                                  required
                                  readonly
                                  placeholder="The apartment id">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="apartmentLocationInputGroup"
                              label="Street & City:"
                              label-for="apartmentCity">
                    <b-row>
                        <b-col>
                            <b-form-input id="apartmentStreetInput"
                                          type="text"
                                          v-model="apartment.street"
                                          required
                                          readonly
                                          placeholder="The street of the apartment">
                            </b-form-input>
                        </b-col>
                        <b-col>
                            <b-form-input id="apartmentCityInput"
                                          type="text"
                                          v-model="apartment.city"
                                          required
                                          readonly
                                          placeholder="The city of the apartment">
                            </b-form-input>
                        </b-col>
                    </b-row>
                </b-form-group>
                <b-form-group id="apartmentStatusInputGroup"
                              label="Status:"
                              label-for="apartmentStatusInput"
                              description="This is the current state of the apartment">
                    <b-form-input id="apartmentStatusInput"
                                  type="text"
                                  required
                                  readonly
                                  v-model="apartment.status">
                    </b-form-input>
                </b-form-group>
                <b-form-group label="Your possibilities:">
                    <b-form-radio-group v-model="selected"
                                        name="selection"
                                        :options="availableStates">
                    </b-form-radio-group>
                </b-form-group>
                <b-button-group>
                    <b-button type="submit" variant="primary">Submit</b-button>
                    <b-button type="reset" variant="danger">Reset</b-button>
                </b-button-group>
            </b-form>
        </div>
    </b-container>
</template>


<script>

    import Axios from 'axios'

    Axios.defaults.baseURL = process.env.VUE_APP_SERVICE_URL + '/api';

    export default {

        name: 'ApartmentRent',

        data() {
            return {
                apartment: {
                    apartmentId: '',
                    city: '',
                    street: '',
                    status: ''
                },
                serviceErrors: [],
                availableStates: [
                    {text: 'Reserve Apartment', value: 'reserved', disabled: false},
                    {text: 'Rent Apartment', value: 'rented', disabled: false},
                    {text: 'Cancel rent', value: 'free', disabled: false},
                ],
                selected: '',
                show: true,
                apartmentRentSuccess: null
            }
        },
        mounted: function () {
            this.callRetrieveApartmentService(this.$route.params.apartmentId);
        },
        updated: function () {
            this.updateAvailableStatesRadioButtons();
        },
        methods: {
            updateAvailableStatesRadioButtons: function () {
                for (const availableStatus of this.availableStates) {
                    availableStatus.disabled = availableStatus.value === this.apartment.status;
                }
            },
            callRetrieveApartmentService(apartmentId) {
                this.serviceErrors = [];

                Axios.get("/apartment/" + apartmentId)
                    .then(response => {
                        this.apartment = response.data;
                    })
                    .catch(error => {
                        this.apartment = null;
                        this.serviceErrors.push(error.message);
                    })
            },
            onSubmit(evt) {
                //evt.preventDefault();

                if (this.selected === "free") {
                    this.callCancelApartmentRentService(this.apartment.apartmentId);
                } else if (this.selected === "rented") {
                    this.callConfirmApartmentRentService(this.apartment.apartmentId);
                } else if (this.selected === "reserved") {
                    this.callReserveApartmentService(this.apartment.apartmentId);
                }
                this.selected = '';

                this.callRetrieveApartmentService(this.apartment.apartmentId);
                //this.updateAvailableStatesRadioButtons();

            },
            onReset(evt) {
                evt.preventDefault();

                this.callRetrieveApartmentService(this.apartment.apartmentId);

                /* Trick to reset/clear native browser form validation state */
                this.show = false;
                this.$nextTick(() => {
                    this.show = true
                });

                this.selected = '';
                //this.updateAvailableStatesRadioButtons();
                this.apartmentRentSuccess = null;
                this.serviceErrors = [];
            },
            callReserveApartmentService(apartmentId) {
                this.serviceErrors = [];

                Axios.post("/apartment-rent/reserve", {"apartmentId": apartmentId})
                    .then(response => {
                        if (response.status === 201) {
                            this.apartmentRentSuccess = "Apartment reserved.";
                        }
                    })
                    .catch(error => {
                        this.apartmentRentSuccess = null;
                        this.serviceErrors.push(error.message);
                    })
            },
            callConfirmApartmentRentService(apartmentId) {
                this.serviceErrors = [];

                Axios.post("/apartment-rent/confirm", {"apartmentId": apartmentId})
                    .then(response => {
                        if (response.status === 201) {
                            this.apartmentRentSuccess = "Apartment rent started.";
                        }
                    })
                    .catch(error => {
                        this.apartmentRentSuccess = null;
                        this.serviceErrors.push(error.message);
                    })
            },
            callCancelApartmentRentService(apartmentId) {
                this.serviceErrors = [];

                Axios.post("/apartment-rent/cancel", {"apartmentId": apartmentId})
                    .then(response => {
                        if (response.status === 201) {
                            this.apartmentRentSuccess = "Apartment rent cancelled.";
                        }
                    })
                    .catch(error => {
                        this.apartmentRentSuccess = null;
                        this.serviceErrors.push(error.message);
                    })
            }
        }
    }
</script>
