<template>
  <div class="hello">
    <!--<billing idnaja="aaa"></billing>-->
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <h3><a v-on:click="billing">Billing</a></h3>
    <ul>
      <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
      <li><a href="https://gitter.im/vuejs/vue" target="_blank">Gitter Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
      <br>
      <li><a href="http://vuejs-templates.github.io/webpack/" target="_blank">Docs for This Template</a></li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
      <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
      <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
    </ul>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'hello',
    data () {
      return {
        msg: 'Welcome sss',
        aaa: 'aaaa'
      }
    },
    methods: {
        billing() {
          console.log('aaaaaaaa', 'billing');
          axios.post('http://localhost:9006/bill', {
            user_id : 1
          })
            .then(
                (response) => {
                  console.log(response);
                  this.$router.push({
                    name: 'Billing',
                    params : { id : response.data.id }
              })}
            )
            .catch(
                (error) => {
              console.log(error);
            });
        }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
