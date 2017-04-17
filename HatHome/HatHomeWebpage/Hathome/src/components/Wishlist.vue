<template>
  <section id="cart_items">
    <div class="container">
      <h2 class="title text-center" style="margin-top:20px;">{{header}}</h2>
      <div class="col-sm-1"></div>
      <div class="col-sm-10">
        <div class="wishlist">
          <div class="table-responsive cart_info">

            <table class="table table-condensed" style="width: 100%;">
              <thead>
              <tr class="cart_menu">
                <td class="image">Item</td>
                <td class="description">Product_id</td>
                <td class="price">Price</td>
                <td></td>
              </tr>
              </thead>
              <tbody>
              <!--test v-for-->
              <!--<tr v-for="wishlist">-->
                <!--<plan :x="plan"></plan>-->
              <!--</tr>-->
              <tr v-for="attribute in products">
                <td><img v-bind:src="attribute.image"/>  </td>
                <td>{{attribute.id}}</td>
                <td>{{attribute.price}}</td>
                <td class="cart_delete">
                  <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                </td>
              </tr>
              <tr>
                <td colspan="1">&nbsp;</td>
                <td colspan="2">
                  <table class="table table-condensed total-result">
                    <tr>
                      <td>
                        <a class="btn btn-default check_out" href="">Buy Wishlist</a></td>
                    </tr>
                  </table>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
        </div>
      </div>
      <div class="col-sm-1"></div>
    </div>
  </section>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'wishlist',
    data () {
      return {
        header: 'My Wishlist',
        product_id: '',
        products : ''
      }
    },
    mounted: function () {
      this.wishlist()
      this.getallproduct()
    },
    methods: {
      wishlist() {
        axios.get('http://localhost:9005/wishlist', {
//          product_id: this.product_id
        })
          .then(
            (response) => {
//              this.$router.push({path: '/'})
              //return all data
              console.log('in then', response.data);
              console.log('in then', response.data.product_id);
              this.product_id = response.data.product_id;
            }
          )
          .catch(
            (error) => {
              console.log('in catch', error);
            }
          )
      },
      getallproduct(){
        axios.get('http://localhost:9004/home',{
        })
          .then(
          (response) => {
            this.products = response.data;
          }
        )
            .catch(
              (error) => {
                  console.log('in catch', error);
              }
            )
      }
    }
  }

//  new Vue({
//    el: "info",
//    data: {
//        plans:[{
//            product_id: '',
//            price: ''
//        }]
//    },
//    components: {
//        plan:{
//            template: "#info",
//            props: ["x"]
//        }
//    }
//  })

</script>
