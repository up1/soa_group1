<template>
  <section id="cart_items">
    <div class="container">
      <h2 class="title text-center" style="margin-top:20px;">MY CART</h2>
      <div class="table-responsive cart_info">
        <table  class="table table-condensed cart_table">
          <thead>
          <tr class="cart_menu">
            <td class="image">Item</td>
            <td class="description"></td>
            <td class="price">Price</td>
            <td class="quantity">Quantity</td>
            <td class="total">Total</td>
            <td></td>
          </tr>
          </thead>
          <tbody id="cart_item">
              <tr v-for="product in products">
                <td class="cart_product">
                  <a href=""><img src="http://i.imgur.com/Ns4cgfm.jpg" alt=""></a>
                </td>
                <td class="cart_description">
                  <h4><a href="">{{product.product.name}}</a></h4>
                </td>
                <td class="cart_price">
                  <p>{{product.product.price}} ฿</p>
                </td>
                <td class="cart_quantity">
                  <div class="cart_quantity_button">
                    <a class="cart_quantity_up" href=""> + </a>
                    <input class="cart_quantity_input" type="text" name="quantity" value="1" autocomplete="off" size="2">
                    <a class="cart_quantity_down" href=""> - </a>
                  </div>
                </td>
                <td class="cart_total">
                  <p class="cart_total_price">฿59</p>
                </td>
                <td class="cart_delete">
                  <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                </td>
              </tr>
          </tbody>
        </table>
        <a v-on:click="billing" class="btn btn-default check_out">Check out your cart</a>
      </div>
    </div>
  </section>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'cart_info',
    data () {
      return {
        products : null
      }
    },
    mounted: function () {
      this.getCartByUserId()
    },
    methods: {
      getCartByUserId(){
        axios.get('http://localhost:9003/cart/' + this.$route.params.userId, {
        }).then(
          (response) => {
            console.log('in then', response.data);
            this.products = response.data;
          }
        ).catch(
          (error) => {
            console.log('in catch', error);
          }
        )
      },
      billing() {
        console.log('aaaaaaaa', 'billing');
        axios.post('http://localhost:9006/bill', {
          user_id : 1234
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
