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
                <b-form-group id="apartmentCityInputGroup"
                              label="City:"
                              label-for="apartmentCity">
                    <b-form-input id="apartmentCityInput"
                                  type="text"
                                  v-model="apartment.city"
                                  required
                                  readonly
                                  placeholder="The city of the apartment">
                    </b-form-input>
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
                    status: ''
                },
                serviceErrors: [],
                availableStates: [
                    {text: 'Reserve Apartment', value: 'reserve', disabled: true},
                    {text: 'Rent Apartment', value: 'rent'},
                    {text: 'Cancel rent', value: 'cancel'},
                ],
                selected: '',
                show: true
            }
        },
        mounted: function () {
            this.callRetrieveApartmentService(this.$route.params.apartmentId)
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
                alert(JSON.stringify(this.selected));
            },
            onReset(evt) {
                evt.preventDefault();
                /* Reset our form values */
                this.callRetrieveApartmentService(this.apartment.apartmentId);
                /* Trick to reset/clear native browser form validation state */
                this.show = false;
                this.$nextTick(() => {
                    this.show = true
                });
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
