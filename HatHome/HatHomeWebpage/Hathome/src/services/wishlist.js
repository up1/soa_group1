import Vue from 'vue'
import axios from 'axios'

class Wishlist {

  addToWishlist (productId, productName, userId) {
    //TODO : get current userId
    console.log(`Adding new product ${productId} to ${userId} wishlist`)
    axios.post('http://localhost:9005/wishlist', {
      user_id: userId,
      product_id: productId
    })
      .then(
        (response) => {
          alert(`Added ${productName} to wishlist`)
          console.log('in then', response.data);
        }
      )
      .catch(
        (error) => {
          console.log('in catch', error);
        }
      )
  }
}
export default Wishlist = new Wishlist();
