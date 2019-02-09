<template>
    <b-container>
        <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>{{apartment}}</h1></div>
    </b-container>
</template>

<script>
    export default {

        name: 'Apartment',

        computed: {
            apartment() {
                return this.$route.params.apartmentId
            }
        },
    };
</script>
