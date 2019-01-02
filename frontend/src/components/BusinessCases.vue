<template>
    <div class="container">
        <div class="BusinessCases">
            <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Apartment rents</h1></div>
            <div style="padding: 1rem;">
                <b-button @click="callRestService()" type="button" class="btn btn-primary" style="margin-bottom: 1rem; margin-right: 1rem;">Start a rent
                </b-button>
                <div>
                    <p>Results: {{ results }}</p>
                </div>
            </div>
            <div style="padding: 1rem;">
                <button @click="callRestService()" type="button" class="btn btn-primary"
                        style="margin-bottom: 1rem; margin-right: 1rem;">Start a rent
                </button>
                <button type="button" class="btn btn-warning" style="margin-bottom: 1rem; margin-right: 1rem;">Abort a
                    rent
                </button>
                <button type="button" class="btn btn-success" style="margin-bottom: 1rem; margin-right: 1rem;">Confirm a
                    rent
                </button>
            </div>
        </div>
    </div>
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
                errors: []
            }
        },

        methods: {
            // Fetches posts when the component is created.
            callRestService() {

                AXIOS.get(`/apartment/start-rent-apartment/1`)
                    .then(response =>  {
                        // JSON responses are automatically parsed.
                        this.results = response.data;
                        console.log(response.data);
                    })
                    .catch(error => {
                        console.log(error);
                        //this.data.errors.push(error);
                    })
            }
        }
    }


</script>
