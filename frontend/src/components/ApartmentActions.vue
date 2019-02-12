<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartment</h1></div>

        <div>
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
                              label="Street / City:"
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
                              label="Current state:"
                              label-for="apartmentStatusInput"
                              description="This is the current state">
                    <b-form-input id="apartmentStatusInput"
                                  type="text"
                                  required
                                  readonly
                                  v-model="apartment.status">
                    </b-form-input>
                </b-form-group>
                <b-form-group label="Your possiblities:">
                    <b-form-radio-group v-model="selected"
                                        name="buttons2"
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

        name: 'ApartmentActions',

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
                show: true
            }
        },
        mounted: function () {
            this.callRetrieveApartmentService(this.$route.params.apartmentId);
        },
        updated: function () {
            for (const availableStatus of this.availableStates) {
                if (availableStatus.value == this.apartment.status) {
                    availableStatus.disabled = true;
                } else {
                    availableStatus.disabled = false;
                }
            }
        },
        methods: {
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
                evt.preventDefault();

                if (this.selected == "free") {
                    this.callCancelApartmentRentService(this.apartment.apartmentId);
                } else if (this.selected == "rented") {
                    this.callConfirmApartmentRentService(this.apartment.apartmentId);
                } else if (this.selected == "reserved") {
                    this.callReserveApartmentService(this.apartment.apartmentId);
                }

                this.callRetrieveApartmentService(this.apartment.apartmentId);

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
            },
            callReserveApartmentService(apartmentId) {
                this.apartmentActionName = "Reserve apartment";
                this.apartmentActionErrors = [];

                Axios.post("/apartment-rent/reserve", {"apartmentId": apartmentId})
                    .then(response => {
                        this.apartmentActionSuccessResponse = response.data;
                    })
                    .catch(error => {
                        this.apartmentActionSuccessResponse = null;
                        this.apartmentActionErrors.push(error.message);
                    })
            },
            callConfirmApartmentRentService(apartmentId) {
                this.apartmentActionName = "Confirm apartment rent";
                this.apartmentActionErrors = [];

                Axios.post("/apartment-rent/confirm", {"apartmentId": apartmentId})
                    .then(response => {
                        this.apartmentActionSuccessResponse = response.data;
                    })
                    .catch(error => {
                        this.apartmentActionSuccessResponse = null;
                        this.apartmentActionErrors.push(error.message);
                    })
            },
            callCancelApartmentRentService(apartmentId) {
                this.apartmentActionName = "Cancel apartment rent";
                this.apartmentActionErrors = [];

                Axios.post("/apartment-rent/cancel", {"apartmentId": apartmentId})
                    .then(response => {
                        this.apartmentActionSuccessResponse = response.data;
                    })
                    .catch(error => {
                        this.apartmentActionSuccessResponse = null;
                        this.apartmentActionErrors.push(error.message);
                    })
            }
        }
    }
</script>
