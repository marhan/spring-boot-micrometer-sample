import Vue from 'vue'
import Router from 'vue-router'
import Apartments from '@/components/Apartments'
import Apartment from '@/components/Apartment'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/apartment/:apartmentId',
            name: 'apartment',
            component: Apartment
        },
        {
            path: '/apartments',
            name: 'apartments',
            component: Apartments
        }
    ]
})
