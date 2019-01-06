<template>
    <div id="app">
        <b-navbar toggleable="md" type="dark" variant="info">

            <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

            <b-navbar-brand :to="{ path: '/' }">{{ appName }}</b-navbar-brand>

            <b-collapse is-nav id="nav_collapse">

                <b-navbar-nav>
                    <b-nav-item :to="{ path: '/' }">Home</b-nav-item>
                    <b-nav-item :to="{ path: 'business-cases' }">Business cases</b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">

                    <b-nav-item-dropdown text="App service hyperlinks" right>
                        <b-dropdown-item :href="appServiceUrl + '/actuator'">All service actuators</b-dropdown-item>
                        <b-dropdown-item :href="appServiceUrl + '/actuator/health'">Health actuator endpoint</b-dropdown-item>
                        <b-dropdown-item :href="appServiceUrl + '/actuator/prometheus'">Prometheus actuator endpoint</b-dropdown-item>
                        <b-dropdown-item href="#">Swagger UI</b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown text="Monitoring hyperlinks" right>
                        <b-dropdown-item href="http://localhost:9090/targets">Prometheus instance</b-dropdown-item>
                        <b-dropdown-item href="http://localhost:3000">Grafana instance</b-dropdown-item>
                        <b-dropdown-item href="http://localhost:9200/">Elasticsearch instance</b-dropdown-item>
                        <b-dropdown-item href="http://localhost:5601">Kibana instance</b-dropdown-item>
                    </b-nav-item-dropdown>

                </b-navbar-nav>

            </b-collapse>
        </b-navbar>
        <!-- navbar-1.vue -->

        <router-view/>

    </div>
</template>
<script>
    export default {
        name: 'app',

        data() {
            return {
                appName: '',
                appServiceUrl: '',
            }
        },
        created: function () {
            this.appName = process.env.VUE_APP_TITLE;
            this.appServiceUrl = process.env.VUE_APP_SERVICE_URL;
        }
    }
</script>
