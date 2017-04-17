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
                <td class="description"></td>
                <td class="price">Price</td>
                <td></td>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td class="cart_product">
                  <a href=""><img src="http://i.imgur.com/Ns4cgfm.jpg" alt=""></a>
                </td>
                <td class="cart_description">
                  <h4><a href="product_id" v-model="product_id">{{product_id}}</a></h4>
                </td>
                <td class="cart_price">
                  <p>฿59</p>
                </td>

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
        product_id: ''
      }
    },
    mounted: function () {
      this.wishlist()
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
              alert('Please try login again');
            }
          )
      }
    }
  }
</script>
