console.log("Ecommerce website");

const paymentStart = () =>{

    console.log("Ecommerce website");

   var amount = document.getElementById("payment_field").innerText;
   
    // var amount=$("#payment_field").val();
  
   console.log(amount);

  if(amount == '' || amount == null || amount == 0.0){
    alert('value required');
    return ;
  }

  $.ajax(
    {
      url:"/create_order",
      // connverting data into json object and adding data with it
      data:JSON.stringify({amount:amount,info:"order_request"}),
      contentType:"application/json",
      type:'POST',
      dataType:'json',

      success:function(response){

        console.log(response);
       
       if(response.status =="created"){
        
        // if we are getting succes response, and status is created
        // open payment form which contains order id and amount
        let options = {

          key:'rzp_test_oP6Z4wsNNSUTPJ',
          amount:response.amount,
          currency:"INR",
          name:"My ecoomerce website",
          description:"add items buy items, your own ecommerce website",
          image:"https://www.x-cart.com/img/16591/ecommerce-p800.jpg",
          order_id:response.id,
          handler:function(response){
            console.log(response.razorpay_payment_id);
            console.log(response.razorpay_order_id);
            console.log(response.razorpay_signature);
            console.log("payment successfull !! ");
         //   alert("congratulations , payment done !!");
         swal("Congratulations !!", "Payment successfull !!", "success");
          },
          prefill:{
            name : "",
            email:" ",
            contact:" "
          },
          "notes": {
            "address": "Welcome to My sachin's Ecommerce website"
            
            },
            "theme": {
            "color": "#3399cc"
            }
            };
            // we will use options as parameter for razorpay api to open form 
            let razorpay = new Razorpay(options);

            razorpay.on("payment.failed",function(response) {
              console.log(response.error.code);
              console.log(response.error.description);
              console.log(response.error.source);
              console.log(response.error.reaspn);
              console.log(response.error.metadata.order_id);
              console.log(response.error.metadata.payment_id);
       //       alert("oooops , payment failed !!");
             swal("ooooopss  !", "Payment failed !!", "error");
            });

            // we are using razorpay open function to open form
            razorpay.open();

        }
    
       },

      
      error:function(error){
        console.log(error);
        alert("something went wrong");
    },
    })
    }
    