<template>
    <div id="app">
        <b-navbar toggleable="md" type="dark" variant="info">

            <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

            <b-navbar-brand href="#">NavBar</b-navbar-brand>

            <b-collapse is-nav id="nav_collapse">

                <b-navbar-nav>
                    <b-nav-item href="#">Link</b-nav-item>
                    <b-nav-item href="#" disabled>Disabled</b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">

                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Search"/>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                    </b-nav-form>

                    <b-nav-item-dropdown text="Lang" right>
                        <b-dropdown-item href="#">EN</b-dropdown-item>
                        <b-dropdown-item href="#">ES</b-dropdown-item>
                        <b-dropdown-item href="#">RU</b-dropdown-item>
                        <b-dropdown-item href="#">FA</b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown right>
                        <!-- Using button-content slot -->
                        <template slot="button-content">
                            <em>User</em>
                        </template>
                        <b-dropdown-item href="#">Profile</b-dropdown-item>
                        <b-dropdown-item href="#">Signout</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>

            </b-collapse>
        </b-navbar>

        <!-- navbar-1.vue -->
        <div class="container">
            <div style="margin-top: 2rem; padding-bottom: 1rem;"><h1>Micrometer Sample</h1></div>
            <!-- Alerts -->
            <h2>Alerts</h2>
            <div style="padding: 1rem;">
                <div v-for="brand in ['success', 'info', 'warning', 'danger']" :key="brand" class="alert"
                     :class="'alert-' + brand">
                    <strong>Well done!</strong> You successfully read this {{brand}} alert message.
                </div>
            </div>
            <h2>Breadcrumb</h2>
            <div style="padding: 1rem;">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Library</a></li>
                    <li class="breadcrumb-item active">Data</li>
                </ol>
            </div>
            <!-- Buttons-->
            <h2>Buttons</h2>
            <div style="padding: 1rem;">
                <div v-for="type in ['', 'outline']" :key="type">
				  <span v-for="brand in ['primary', 'secondary', 'success', 'info', 'warning', 'danger']" :key="brand">
					  <button role="button" class="btn" :class="'btn-' + (type.length ? (type + '-') : '') + brand"
                              style="margin-bottom: 1rem; margin-right: 1rem;">
						  {{brand}} {{type}}
					  </button>
				  </span>
                </div>
            </div>
            <!-- Progress-->
            <h2>Progress</h2>
            <div style="padding: 1rem;">
                <div v-for="brand in ['success', 'info', 'warning', 'danger']" :key="brand" class="progress"
                     style='margin-bottom: 1rem;'>
                    <div class="progress-bar" :class="'bg-' + brand" style="width: 25%" aria-valuenow="25"
                         aria-valuemin="0" aria-valuemax="100"></div>
                </div>
            </div>
            <!-- Notice-->
            <h2>Modal</h2>
            <div style="padding: 1rem;">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Modal body text goes here.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary">Save changes</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

