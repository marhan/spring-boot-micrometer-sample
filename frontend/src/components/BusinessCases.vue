<template>
    <b-container>
        <div class="BusinessCases">
            <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartments</h1></div>
            <b-row>
                <b-col cols="3">
                    <b-button @click="callReserveApartmentService()" variant="outline-primary">Reserve an apartment
                    </b-button>
                </b-col>
                <b-col>
                    <b-alert :show="showReserveApartmentServiceError===false && results.length > 0"
                             variant="success">
                        <h4>Success</h4>
                        <hr>
                        <p class="mb-0">{{ results }}</p>
                    </b-alert>
                    <b-alert :show="showReserveApartmentServiceError"
                             @dismissed="showReserveApartmentServiceError=false"
                             dismissible
                             v-for="error in errors" :key="error"
                             variant="danger">
                        <h4>Error</h4>
                        <hr>
                        <p class="mb-0">{{ error }}</p>
                    </b-alert>
                </b-col>
            </b-row>
            <div class="row">
                <div style="padding: 1rem;">
                    <button @click="callReserveApartmentService()" type="button" class="btn btn-primary"
                            style="margin-bottom: 1rem; margin-right: 1rem;">Start a rent
                    </button>
                    <button type="button" class="btn btn-warning" style="margin-bottom: 1rem; margin-right: 1rem;">Abort a rent
                    </button>
                    <button type="button" class="btn btn-success" style="margin-bottom: 1rem; margin-right: 1rem;">
                        Confirm a rent
                    </button>
                </div>
            </div>
        </div>
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
                results: [],
                errors: [],
                showReserveApartmentServiceError: false,
            }
        },

        methods: {
            // Fetches posts when the component is created.
            callReserveApartmentService() {

                AXIOS.get(`/apartment/start-rent-apartment/1`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.results = response.data;

                    })
                    .catch(error => {
                        this.errors = [];
                        this.errors.push(error.message);
                        this.showReserveApartmentServiceError = true;
                    })
            }
        }
    }


</script>
