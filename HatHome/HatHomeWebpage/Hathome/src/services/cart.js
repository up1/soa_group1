import axios from 'axios'

class Cart {

  addToCart (productId, productName, userName) {
    let userId = userName
    //TODO : get current userId
    console.log(`Adding new product ${productId} to ${userId} cart`)

    axios.post('http://localhost:9003/cart', {
      user_id: userId,
      product_id: productId
    })
      .then(
        (response) => {
          alert(`Added ${productName} to cart`)
        }
      )
      .catch(
        (error) => {
          console.log('in catch', error);
        }
      )
  }

  removeInCart (cartItemId, productName){

    console.log(`Removing ${cartItemId} - ${productName} from cart`)
    return axios.delete('http://localhost:9003/cart/' + cartItemId)
      .then(
        (response) => {
          alert(`Removed ${productName} from cart`)
        }
      ).catch(
      (error) => {
        console.log('in catch', error);
      }
    )
  }
}

export default Cart = new Cart();
