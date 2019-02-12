<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartment</h1></div>
        <div>
            <b-row>
                <b-col>
                    <b-alert :show="serviceErrors.length <= 0 && apartmentUpdateSuccess !== null" variant="success">
                        <h4>Update successful</h4>
                        <hr>
                        <p class="mb-0">{{ apartmentUpdateSuccess }}</p>
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
                              label="Street / City:"
                              label-for="apartmentLocation">
                    <b-row>
                        <b-col>
                            <b-form-input id="apartmentStreetInput"
                                          type="text"
                                          v-model="form.street"
                                          required
                                          placeholder="The street of the apartment">
                            </b-form-input>
                        </b-col>
                        <b-col>
                            <b-form-input id="apartmentCityInput"
                                          type="text"
                                          v-model="form.city"
                                          required
                                          placeholder="The city of the apartment">
                            </b-form-input>
                        </b-col>
                    </b-row>
                </b-form-group>
                <b-form-group id="apartmentStatusInputGroup"
                              label="Status:"
                              label-for="apartmentStatusInput">
                    <b-form-input id="apartmentStatusInput"
                                  type="text"
                                  required
                                  readonly
                                  v-model="apartment.status">
                    </b-form-input>
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

        name: 'ApartmentEdit',

        data() {
            return {
                apartment: {
                    apartmentId: '',
                    street: '',
                    city: '',
                    status: ''
                },
                serviceErrors: [],
                form: {
                    street: '',
                    city: ''
                },
                availableStatus: [
                    'free', 'reserved', 'rented'
                ],
                show: true,
                apartmentUpdateSuccess: null,
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
                        this.form.street = this.apartment.street;
                        this.form.city = this.apartment.city;
                    })
                    .catch(error => {
                        this.apartment = null;
                        this.serviceErrors.push(error.message);
                    })
            },
            onSubmit(evt) {
                evt.preventDefault();
                this.callUpdateApartmentService(this.apartment.apartmentId, this.form);
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
                this.apartmentUpdateSuccess = null;
                this.serviceErrors = [];
            },
            callUpdateApartmentService(apartmentId, form) {
                this.serviceErrors = [];

                Axios.put("/apartment/" + apartmentId,
                    {
                        "street": form.street,
                        "city": form.city
                    })
                    .then(response => {
                        this.apartment = response.data;
                        this.apartmentUpdateSuccess = "Apartment updated.";
                    })
                    .catch(error => {
                        this.apartmentUpdateSuccess = null;
                        this.serviceErrors.push(error.message);
                    })
            }
        }
    }
</script>
