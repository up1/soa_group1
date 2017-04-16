<template>
  <div class="billing">
    <section id="cart_items">
      <div class="container">
        <h2 class="title text-center" text-centerstyle="margin-top:20px;">Billing</h2>
        <div class="bill-infomation">
          <p align="left" class="user-id"><b>User ID: </b> {{user_id}} </p>
          <p align="left" class="user-name"><b>Name: </b> {{username}} </p>
          <p align="left" class="bill-address"><b>Address: </b> {{bill_address}} </p>
          <p align="left" class="bill-no"><b>Bill No: </b> {{bill_id}} </p>
          <p align="left" class="bill-date"><b>Bill Date: </b> {{bill_date}} </p>
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
            <tr>
              <td class="cart_product">
                <a href=""><img src="http://i.imgur.com/Ns4cgfm.jpg" alt=""></a>
              </td>
              <td class="cart_description">
                <h4><a href="">Colorblock Scuba</a></h4>
              </td>
              <td class="cart_price">
                <p>฿59</p>
              </td>
              <td class="cart_quantity">
                <p>2</p>
              </td>
              <td class="cart_total">
                <p class="cart_total_price">฿59</p>
              </td>

            </tr>

            <tr>
              <td class="cart_product">
                <a href=""><img src="http://i.imgur.com/hflVXay.jpg" alt=""></a>
              </td>
              <td class="cart_description">
                <h4><a href="">Colorblock Scuba</a></h4>

              </td>
              <td class="cart_price">
                <p>฿59</p>
              </td>
              <td class="cart_quantity">
                <p>5</p>
              </td>
              <td class="cart_total">
                <p class="cart_total_price">฿59</p>
              </td>

            </tr>
            <tr>
              <td class="cart_product">
                <a href=""><img src="http://i.imgur.com/kj7tXU7.jpg" alt=""></a>
              </td>
              <td class="cart_description">
                <h4><a href="">Colorblock Scuba</a></h4>

              </td>
              <td class="cart_price">
                <p>฿59</p>
              </td>
              <td class="cart_quantity">
                <p>1</p>
              </td>
              <td class="cart_total">
                <p class="cart_total_price">฿59</p>
              </td>

            </tr>
            <tr>
              <td colspan="3">&nbsp;</td>
              <td colspan="2">
                <table class="table table-condensed total-result">
                  <tr>
                    <td>Cart Sub Total</td>
                    <td>฿{{ cart_cost }}</td>
                  </tr>
                  <tr class="shipping-cost">
                    <td>Shipping Cost</td>
                    <td>฿{{ shipping_cost }}</td>
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
        user_id: 1,
        username: 'username',
        bill_id: 1,
        bill_address: 'bill_address',
        bill_date: 'bill_date',
        cart_cost: 'cart_cost',
        shipping_cost: 'shipping_cost',
        total: 'total'
      }
    },
    mounted: function () {
        this.callBilling()
    },
    methods: {
      callBilling: function () {
        axios.get('http://localhost:9006/bill/1', {
        })
          .then((response) => {
            console.log(response)
            this.bill_id = response.data.id
            this.user_id = response.data.user_id
            this.bill_address = response.data.address
            this.bill_date = response.data.date
            this.cart_cost = response.data.cart_cost
            this.shipping_cost = response.data.shipping_cost
            this.total = response.data.total
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
