import Vue from 'vue'
import Router from 'vue-router'
import Apartments from '@/components/Apartments'
import ApartmentEdit from '@/components/ApartmentEdit'
import ApartmentRent from '@/components/ApartmentRent'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/apartmentEdit/:apartmentId',
            name: 'ApartmentEdit',
            component: ApartmentEdit
        },
        {
            path: '/apartmentRent/:apartmentId',
            name: 'ApartmentRent',
            component: ApartmentRent
        },
        {
            path: '/apartments',
            name: 'Apartments',
            component: Apartments
        }
    ]
})
