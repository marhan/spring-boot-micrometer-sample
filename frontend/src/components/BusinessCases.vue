<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartments</h1></div>
        <b-table :fields="apartmentsFields" :items="apartments" style="margin-top: 1rem; padding-bottom: 1rem;" striped hover :bordered="bordered"
                 :outlined="outlined">
            <template slot="show_details" slot-scope="row">
                <!-- we use @click.stop here to prevent emitting of a 'row-clicked' event  -->
                <b-button size="sm" @click.stop="row.toggleDetails" class="mr-2">
                    {{ row.detailsShowing ? 'Hide' : 'Show'}} Actions
                </b-button>
            </template>
            <template slot="row-details" slot-scope="row">
                <b-card>
                    <!-- reserve -->
                    <b-row style="margin-top: 1rem; padding-bottom: 1rem;">
                        <b-col cols="3">
                            <b-button @click="callReserveApartmentService()" variant="outline-primary">Reserve an apartment
                            </b-button>
                        </b-col>
                        <b-col>
                            <b-alert :show="reserveApartmentErrors.length <= 0 && reserveApartmentResponse !== null" variant="success">
                                <h4>Apartment successfully reserved</h4>
                                <hr>
                                <p class="mb-0">{{ reserveApartmentResponse }}</p>
                            </b-alert>
                            <b-alert :show="reserveApartmentErrors.length > 0"
                                     v-for="error in reserveApartmentErrors" :key="error"
                                     variant="danger">
                                <h4>Error</h4>
                                <hr>
                                <p class="mb-0">{{ error }}</p>
                            </b-alert>
                        </b-col>
                    </b-row>
                    <hr>
                    <!-- confirm -->
                    <b-row style="margin-top: 2rem; padding-bottom: 1rem;">
                        <b-col cols="3">
                            <b-button @click="callConfirmApartmentRentService()" variant="outline-success">Confirm apartment rent
                            </b-button>
                        </b-col>
                        <b-col>
                            <b-alert :show="confirmApartmentRentErrors.length <= 0 && confirmApartmentRentResponse !== null" variant="success">
                                <h4>Apartment rent successful started</h4>
                                <hr>
                                <p class="mb-0">{{ confirmApartmentRentResponse }}</p>
                            </b-alert>
                            <b-alert :show="confirmApartmentRentErrors.length > 0"
                                     v-for="error in confirmApartmentRentErrors" :key="error"
                                     variant="danger">
                                <h4>Error</h4>
                                <hr>
                                <p class="mb-0">{{ error }}</p>
                            </b-alert>
                        </b-col>
                    </b-row>
                    <hr>
                    <!-- cancel -->
                    <b-row style="margin-top: 2rem; padding-bottom: 1rem;">
                        <b-col cols="3">
                            <b-button @click="callCancelApartmentRentService()" variant="outline-warning">Cancel apartment rent
                            </b-button>
                        </b-col>
                        <b-col>
                            <b-alert :show="cancelApartmentRentErrors.length <= 0 && cancelApartmentRentResults !== null" variant="success">
                                <h4>Rent successful cancelled</h4>
                                <hr>
                                <p class="mb-0">{{ cancelApartmentRentResults }}</p>
                            </b-alert>
                            <b-alert :show="cancelApartmentRentErrors.length > 0"
                                     v-for="error in cancelApartmentRentErrors" :key="error"
                                     variant="danger">
                                <h4>Error</h4>
                                <hr>
                                <p class="mb-0">{{ error }}</p>
                            </b-alert>
                        </b-col>
                    </b-row>
                </b-card>
            </template>
        </b-table>
    </b-container>
</template>

<script>
    /*eslint no-console: "error"*/

    import Axios from 'axios'

    Axios.defaults.baseURL = process.env.VUE_APP_SERVICE_URL;

    export default {

        name: 'BusinessCases',

        data() {
            return {
                apartmentsFields: ['apartmentId', 'city', 'status', 'show_details'],
                apartments: [],
                apartmentErrors: [],
                reserveApartmentResponse: null,
                reserveApartmentErrors: [],
                confirmApartmentRentResponse: null,
                confirmApartmentRentErrors: [],
                cancelApartmentRentResults: null,
                cancelApartmentRentErrors: [],
                bordered: true,
                outlined: true
            }
        },
        mounted: function () {
            this.callRetrieveApartmentService();
        },
        methods: {
            callRetrieveApartmentService() {
                this.apartmentErrors = [];

                Axios.get("/apartment")
                    .then(response => {
                        this.apartments = response.data;
                    })
                    .catch(error => {
                        this.apartments = [];
                        this.apartmentErrors.push(error.message);
                    })
            },
            callReserveApartmentService() {
                this.reserveApartmentErrors = [];

                Axios.post("/apartment-rent/reserve", {"apartmentId": 1})
                    .then(response => {
                        this.reserveApartmentResponse = response.data;
                    })
                    .catch(error => {
                        this.reserveApartmentResponse = null;
                        this.reserveApartmentErrors.push(error.message);
                    })
            },
            callConfirmApartmentRentService() {
                this.confirmApartmentRentErrors = [];

                Axios.post("/apartment-rent/confirm", {"apartmentId": 1})
                    .then(response => {
                        this.confirmApartmentRentResponse = response.data;
                    })
                    .catch(error => {
                        this.confirmApartmentRentResponse = null;
                        this.confirmApartmentRentErrors.push(error.message);
                    })
            },
            callCancelApartmentRentService() {
                this.cancelApartmentRentErrors = [];

                Axios.post("/apartment-rent/cancel", {"apartmentId": 1})
                    .then(response => {
                        this.cancelApartmentRentResults = response.data;
                    })
                    .catch(error => {
                        this.cancelApartmentRentResults = null;
                        this.cancelApartmentRentErrors.push(error.message);
                    })
            }
        }
    }

</script>
