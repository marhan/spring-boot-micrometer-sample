<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartments</h1></div>
        <b-row>
            <b-col>
                <b-alert :show="serviceErrors.length > 0"
                         v-for="error in serviceErrors" :key="error"
                         variant="danger">
                    <h4>Could not retrieve apartment list!</h4>
                    <hr>
                    <p class="mb-0">{{ error }}</p>
                </b-alert>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <b-alert :show="apartmentActionErrors.length <= 0 && apartmentActionSuccessResponse !== null" variant="success">
                    <h4>{{ apartmentActionName }}</h4>
                    <hr>
                    <p class="mb-0">{{ apartmentActionSuccessResponse }}</p>
                </b-alert>
                <b-alert :show="apartmentActionErrors.length > 0"
                         v-for="error in apartmentActionErrors" :key="error"
                         variant="danger">
                    <h4>{{ apartmentActionName }}</h4>
                    <hr>
                    <p class="mb-0">{{ error }}</p>
                </b-alert>
            </b-col>
        </b-row>
        <b-table :fields="apartmentsFields" :items="apartments" style="margin-top: 1rem; padding-bottom: 1rem;" striped hover :bordered="bordered" :outlined="outlined">
            <template slot="actions" slot-scope="row">
                <b-button @click.stop="row.toggleDetails" class="mr-2" :to="'/apartmentEdit/' + row.item.apartmentId" variant="info">Edit</b-button>
                <b-button @click.stop="row.toggleDetails" class="mr-2" :to="'/apartmentActions/' + row.item.apartmentId" variant="info">Reservation</b-button>
            </template>
        </b-table>
    </b-container>
</template>

<script>
    /*eslint no-console: "error"*/

    import Axios from 'axios'

    Axios.defaults.baseURL = process.env.VUE_APP_SERVICE_URL + '/api';

    export default {

        name: 'Apartments',

        data() {
            return {
                apartmentsFields: ['apartmentId', 'street', 'city', 'status', 'actions'],
                apartments: [],
                serviceErrors: [],
                apartmentActionName: null,
                apartmentActionErrors: [],
                apartmentActionSuccessResponse: null,
                bordered: true,
                outlined: true
            }
        },
        mounted: function () {
            this.callRetrieveApartmentService();
        },
        methods: {
            callRetrieveApartmentService() {
                this.apartmentActionName = "Retrieve apartments";
                this.serviceErrors = [];

                Axios.get("/apartment")
                    .then(response => {
                        this.apartments = response.data;
                        this.apartmentActionSuccessResponse = "Apartment list retrieved successfully. " +
                            "Select the apartment you want to modify and apply an action.";
                    })
                    .catch(error => {
                        this.apartments = [];
                        this.apartmentActionSuccessResponse = null;
                        this.serviceErrors.push(error.message);
                    })
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
