import Vue from 'vue';
import Router from 'vue-router';
import PeopleList from './views/PeopleList.vue';
import PersonEdit from './views/PersonEdit.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/people',
    },
    {
      path: '/people',
      name: 'people',
      component: PeopleList,
    },
    {
      path: '/people/:id',
      name: 'person-edit',
      component: PersonEdit,
    },
  ],
});
