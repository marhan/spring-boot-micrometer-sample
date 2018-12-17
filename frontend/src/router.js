import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import BusinessCases from '@/components/BusinessCases'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/business-cases',
      name: 'Business cases',
      component: BusinessCases
    }
  ]
})
