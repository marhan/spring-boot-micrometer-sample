import Vue from 'vue'
import Router from 'vue-router'
import Apartments from '@/components/Apartments'
import ApartmentEdit from '@/components/ApartmentEdit'
import ApartmentActions from '@/components/ApartmentActions'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/apartmentEdit/:apartmentId',
            name: 'ApartmentEdit',
            component: ApartmentEdit
        },
        {
            path: '/apartmentActions/:apartmentId',
            name: 'ApartmentActions',
            component: ApartmentActions
        },
        {
            path: '/apartments',
            name: 'Apartments',
            component: Apartments
        }
    ]
})
