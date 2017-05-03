import Vue from 'vue'
import axios from 'axios'

class Wishlist {

  addToWishlist (productId, userId) {
    return axios.post('http://188.166.233.180:9005/wishlist', {
      userId: userId,
      productId: productId
    })
      .then(
        (response) => {
          console.log('can add')
        }
      )
      .catch(
        (error) => {
          console.log('in catch', error);
        }
      )
  }

  deleteFromWishlist (productId, userId) {
    console.log(`DELETE`);
    return axios.delete('http://188.166.233.180:9005/wishlist/' + productId + '/user/' + userId)
      .then(
        (response) => {
          console.log('can delete')
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
