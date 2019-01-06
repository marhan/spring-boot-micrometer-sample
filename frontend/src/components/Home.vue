<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartments</h1></div>
        <b-row>
            <b-col>
                <b-alert :show="apartmentErrors.length > 0"
                         v-for="error in apartmentErrors" :key="error"
                         variant="danger">
                    <h4>Could not retrieve apartments</h4>
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
                    <b-row>
                        <b-col>
                            <b-button @click="callReserveApartmentService(row.item.apartmentId)" variant="outline-primary">Reserve an apartment
                            </b-button>
                        </b-col>
                        <b-col>
                            <b-button @click="callConfirmApartmentRentService(row.item.apartmentId)" variant="outline-success">Confirm apartment rent
                            </b-button>
                        </b-col>
                        <b-col>
                            <b-button @click="callCancelApartmentRentService(row.item.apartmentId)" variant="outline-warning">Cancel apartment rent
                            </b-button>
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

    Axios.defaults.baseURL = process.env.VUE_APP_SERVICE_URL + '/api';

    export default {

        name: 'BusinessCases',

        data() {
            return {
                apartmentsFields: ['apartmentId', 'city', 'status', 'show_details'],
                apartments: [],
                apartmentErrors: [],
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
                this.apartmentErrors = [];

                Axios.get("/apartment")
                    .then(response => {
                        this.apartments = response.data;
                        this.apartmentActionSuccessResponse = "Apartment list retrieved successfully. Select the apartment apply the action you want.";
                    })
                    .catch(error => {
                        this.apartments = [];
                        this.apartmentActionSuccessResponse = null;
                        this.apartmentErrors.push(error.message);
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
