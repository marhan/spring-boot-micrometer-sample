<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>{{ apartment }}</h1></div>
    </b-container>
</template>

<script>

    import Axios from 'axios'

    Axios.defaults.baseURL = process.env.VUE_APP_SERVICE_URL + '/api';
    ;

    export default {

        name: 'Apartment',

        data() {
            return {
                apartment: null,
                serviceErrors: []
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
            }
        }
    };
</script>
