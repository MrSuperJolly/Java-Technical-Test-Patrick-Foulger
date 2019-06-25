<template>
  <div>
    <h2 class="title">Update {{person | fullName}}</h2>

    <div class="form">
      <fieldset class="box">
        <legend>User Details</legend>

        <div class="field">
          <div class="control">
            <label class="checkbox">
              <input type="checkbox" v-model="person.authorised"> Authorised
            </label>
          </div>

          <div class="control">
            <label class="checkbox">
              <input type="checkbox" v-model="person.enabled"> Enabled
            </label>
          </div>
        </div>
      </fieldset>

      <fieldset class="box">
        <legend>Favourite Colours</legend>

        <div class="control" v-for="colour of colours" :key="colour.id">
          <label class="checkbox">
            <input type="checkbox" :value="colour" v-model="person.colours"> {{colour.name}}
          </label>
        </div>
      </fieldset>

      <div class="field is-grouped">
        <div class="control">
          <button class="button is-link" v-on:click="submit()">Save Changes</button>
        </div>

        <div class="control">
          <router-link class="button is-light" :to="{name: 'people'}">Cancel</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import { getColours } from '../api/colours-api';
import { getPerson, updatePerson } from '../api/people-api';

export default Vue.extend({
  async beforeMount() {
    const personId = this.$route.params.id;

    this.person = await getPerson(personId);
    this.colours = await getColours();
  },
  data() {
    const person: IPerson = {
      firstName: '',
      lastName: '',
    } as IPerson;
    const colours: IColour[] = [];

    return {
      person,
      colours,
    };
  },
  filters: {
    fullName: (person: IPerson): string => {
      return `${person.firstName} ${person.lastName}`;
    },
  },
  methods: {
    async submit() {
      const success = await updatePerson(this.person);

      if (success) {
        this.$router.push({name: 'people'});
      }
    },
  },
});
</script>

<style lang="sass">

</style>
