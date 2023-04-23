import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LogInView from '../views/LogInView.vue'
import SignInView from '../views/SignInView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LogInView
    },
    {
      path: '/signin',
      name: 'signin',
      component: ()=> import('../views/SignInView.vue')
    },
   
    {
      path: '/myEvents',
      name: 'myEvents',
      component: () => import('../views/MyEventsView.vue')
    },
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')

    },
    {
      path: '/favoriteBands',
      name: 'favoriteBands',
      component: () => import('../views/FavoriteBandsView.vue')
    },
    {
      path:'/MuroAdmin',
      name: 'MuroAdmin',
      component: () => import('../views/MuroAdminView.vue')
    },
    {
      path:'/UsersAdmin',
      name: 'UsersAdmin',
      component: () => import('../views/UsersAdminView.vue')   
    },
    
   ]
})

export default router
