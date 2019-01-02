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
        <!-- confirm -->
        <b-row>
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
    </b-container>
</template>

<script>
    /*eslint no-console: "error"*/

    // import axios from 'axios'
    import {AXIOS} from './http-common'

    export default {

        name: 'BusinessCases',

        data() {
            return {
                reserveApartmentResults: [],
                reserveApartmentErrors: [],
                showReserveApartmentServiceError: false,
                confirmApartmentRentResults: [],
                confirmApartmentRentErrors: [],
                showConfirmApartmentRentServiceError: false
            }
        },

        methods: {
            // Fetches posts when the component is created.
            callReserveApartmentService() {

                AXIOS.get(`/apartment/reserve-apartment/1`)
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
                AXIOS.get(`/apartment/confirm-apartment-rent/1`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.confirmApartmentRentResults = response.data;

                    })
                    .catch(error => {
                        this.confirmApartmentRentErrors = [];
                        this.confirmApartmentRentErrors.push(error.message);
                        this.showConfirmApartmentRentServiceError = true;
                    })
            }
        }
    }


</script>
