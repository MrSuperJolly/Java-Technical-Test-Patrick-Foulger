<template>
  <div>
    <nav class="navbar is-light">
      <div class="container">
        <div class="navbar-brand">
          <router-link to="/" class="navbar-item">Colours</router-link>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view/>
    </div>
  </div>
</template>

<style lang="scss">
@import "~bulma";

html {
  overflow-y: auto;
}

fieldset {
  legend {
    background: white;
  }
}
</style>
