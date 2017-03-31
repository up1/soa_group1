/**
 * Created by Plern on 19/3/2560.
 */
$(document).ready(function() {
    $.ajax({
        type:'GET',
        dataType: 'json',
        url: "http://localhost:9005/wishlist",
        success: function(data, status) {
            console.log(data);
            $('.wishlist_id').append(data.wishlist_id);
            $('.user_id').append(data.user_id);
            $('.product_id').append(data.product_id);
        }
    });
});