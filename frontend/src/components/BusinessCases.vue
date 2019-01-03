<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartments</h1></div>
        <!-- reserve -->
        <b-row style="margin-top: 1rem; padding-bottom: 1rem;">
            <b-col cols="3">
                <b-button @click="callReserveApartmentService()" variant="outline-primary">Reserve an apartment
                </b-button>
            </b-col>
            <b-col>
                <b-alert :show="showReserveApartmentServiceError===false && reserveApartmentResponse !== null"
                         variant="success">
                    <h4>Apartment successfully reserved</h4>
                    <hr>
                    <p class="mb-0">{{ reserveApartmentResponse }}</p>
                </b-alert>
                <b-alert :show="showReserveApartmentServiceError"
                         @dismissed="showReserveApartmentServiceError=false"
                         dismissible
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
                <b-alert :show="showConfirmApartmentRentServiceError===false && confirmApartmentRentResponse !== null"
                         variant="success">
                    <h4>Apartment rent successful started</h4>
                    <hr>
                    <p class="mb-0">{{ confirmApartmentRentResponse }}</p>
                </b-alert>
                <b-alert :show="showConfirmApartmentRentServiceError"
                         @dismissed="showConfirmApartmentRentServiceError=false"
                         dismissible
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
                <b-alert :show="showCancelApartmentRentServiceError===false && cancelApartmentRentResults !== null"
                         variant="success">
                    <h4>Rent successful cancelled</h4>
                    <hr>
                    <p class="mb-0">{{ cancelApartmentRentResults }}</p>
                </b-alert>
                <b-alert :show="showCancelApartmentRentServiceError"
                         @dismissed="showCancelApartmentRentServiceError=false"
                         dismissible
                         v-for="error in cancelApartmentRentErrors" :key="error"
                         variant="danger">
                    <h4>Error</h4>
                    <hr>
                    <p class="mb-0">{{ error }}</p>
                </b-alert>
            </b-col>
        </b-row>
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
                reserveApartmentResponse: null,
                reserveApartmentErrors: [],
                showReserveApartmentServiceError: false,
                confirmApartmentRentResponse: null,
                confirmApartmentRentErrors: [],
                showConfirmApartmentRentServiceError: false,
                cancelApartmentRentResults: null,
                cancelApartmentRentErrors: [],
                showCancelApartmentRentServiceError: false
            }
        },
        methods: {
            // Fetches posts when the component is created.
            callReserveApartmentService() {

                Axios.post('/apartment/reserve-apartment', {"apartmentId": 1})
                    .then(response => {
                        this.reserveApartmentResponse = response.data;
                    })
                    .catch(error => {
                        this.reserveApartmentResponse = null;
                        this.reserveApartmentErrors = [];
                        this.reserveApartmentErrors.push(error.message);
                        this.showReserveApartmentServiceError = true;
                    })
            },
            callConfirmApartmentRentService() {
                Axios.post("/apartment/confirm-apartment-rent", {"apartmentId": 1})
                    .then(response => {
                        this.confirmApartmentRentResponse = response.data;
                    })
                    .catch(error => {
                        this.confirmApartmentRentResponse = null;
                        this.confirmApartmentRentErrors = [];
                        this.confirmApartmentRentErrors.push(error.message);
                        this.showConfirmApartmentRentServiceError = true;
                    })
            },
            callCancelApartmentRentService() {
                Axios.post("/apartment/cancel-apartment-rent", {"apartmentId": 1})
                    .then(response => {
                        this.cancelApartmentRentResults = response.data;
                    })
                    .catch(error => {
                        this.cancelApartmentRentResults = null;
                        this.cancelApartmentRentErrors = [];
                        this.cancelApartmentRentErrors.push(error.message);
                        this.showCancelApartmentRentServiceError = true;
                    })
            }
        }
    }

</script>
