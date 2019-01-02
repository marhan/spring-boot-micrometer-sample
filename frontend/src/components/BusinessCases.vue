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
                <b-alert :show="showReserveApartmentServiceError===false && reserveApartmentResults.length > 0"
                         variant="success">
                    <h4>Successful reserved</h4>
                    <hr>
                    <p class="mb-0">{{ reserveApartmentResults }}</p>
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
                <b-alert :show="showConfirmApartmentRentServiceError===false && confirmApartmentRentResults.length > 0"
                         variant="success">
                    <h4>Rent successful started</h4>
                    <hr>
                    <p class="mb-0">{{ confirmApartmentRentResults }}</p>
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
                <b-alert :show="showCancelApartmentRentServiceError===false && cancelApartmentRentResults.length > 0"
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
                reserveApartmentResults: [],
                reserveApartmentErrors: [],
                showReserveApartmentServiceError: false,
                confirmApartmentRentResults: [],
                confirmApartmentRentErrors: [],
                showConfirmApartmentRentServiceError: false,
                cancelApartmentRentResults: [],
                cancelApartmentRentErrors: [],
                showCancelApartmentRentServiceError: false
            }
        },
        methods: {
            // Fetches posts when the component is created.
            callReserveApartmentService() {

                Axios.get(`/apartment/reserve-apartment/1`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.reserveApartmentResults = response.data;

                    })
                    .catch(error => {
                        this.reserveApartmentErrors = [];
                        this.reserveApartmentErrors.push(error.message);
                        this.showReserveApartmentServiceError = true;
                    })
            },
            callConfirmApartmentRentService() {
                Axios.get(`/apartment/confirm-apartment-rent/1`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.confirmApartmentRentResults = response.data;

                    })
                    .catch(error => {
                        this.confirmApartmentRentErrors = [];
                        this.confirmApartmentRentErrors.push(error.message);
                        this.showConfirmApartmentRentServiceError = true;
                    })
            },
            callCancelApartmentRentService() {
                Axios.get(`/apartment/cancel-apartment-rent/1`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.cancelApartmentRentResults = response.data;

                    })
                    .catch(error => {
                        this.cancelApartmentRentErrors = [];
                        this.cancelApartmentRentErrors.push(error.message);
                        this.showCancelApartmentRentServiceError = true;
                    })
            }
        }
    }

</script>
