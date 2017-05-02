<template>
  <div class="billing">
    <section id="cart_items">
      <div class="container">
        <h2 class="title text-center" text-centerstyle="margin-top:20px;">Billing</h2>
        <div class="bill-infomation">
          <p align="left" class="user-id"><b>User ID: </b> {{userId}} </p>
          <p align="left" class="user-name"><b>Name: </b> {{username}} </p>
          <p align="left" class="bill-address"><b>Address: </b> {{billAddress}} </p>
          <p align="left" class="bill-no"><b>Bill No: </b> {{billId}} </p>
          <p align="left" class="bill-date"><b>Bill Date: </b> {{billDate}} </p>
        </div>

        <div class="table-responsive cart_info">
          <table class="table table-condensed">
            <thead>
            <tr class="cart_menu">
              <td class="image">Item</td>
              <td class="description"></td>
              <td class="price">Price</td>
              <td class="quantity">Quantity</td>
              <td class="total">Total</td>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in products">
              <td class="cart_product">
                <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'" style="width: 120px; height: 120px;"/>
              </td>
              <td class="cart_description">
                <h4>{{item.name}}</h4>
              </td>
              <td class="cart_price">
                <p>฿{{item.price}}</p>
              </td>
              <td class="cart_quantity">
                <p>{{item.amount}}</p>
              </td>
              <td class="cart_total">
                <p class="cart_total_price">฿{{item.price * item.amount}}</p>
              </td>

            </tr>

            <tr>
              <td colspan="3">&nbsp;</td>
              <td colspan="2">
                <table class="table table-condensed total-result">
                  <tr>
                    <td>Cart Sub Total</td>
                    <td>฿{{ cartPrice }}</td>
                  </tr>
                  <tr class="shipping-cost">
                    <td>Shipping Cost</td>
                    <td>฿{{ shippingCost }}</td>
                  </tr>
                  <tr>
                    <td style="font-size:20px;">Total</td>
                    <td><span style="font-size:20px;">฿{{total}}</span></td>
                  </tr>

                </table>
              </td>
            </tr>
            </tbody>

          </table>

        </div>
      </div>
    </section>
  </div>
</template>


<script>
  import axios from 'axios'
  export default {
    name: 'billing',
    data () {
      return {
        userId: '',
        username: '',
        billId: '',
        billAddress: '',
        billDate: '',
        cartPrice: '',
        shippingCost: '',
        total: '',
        image: '',
        cart: '',
        products: []
      }
    },
    mounted: function () {
        this.callBilling()
    },
    methods: {
      callBilling: function () {
        axios.get('http://128.199.153.227:9006/bill/' + this.$route.params.id , {})
          .then((response) => {
            console.log(response)
            this.billId = response.data.id
            this.userId = response.data.userId
            this.username = response.data.username
            this.billAddress = response.data.address
            this.billDate = response.data.date
            this.cartPrice = response.data.cartPrice
            this.shippingCost = response.data.shippingCost
            this.total = response.data.total
            this.products = response.data.cart.products
           })
          .catch(function (error) {
            console.log(error)
          })
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
