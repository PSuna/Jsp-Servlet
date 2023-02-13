<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!--  JSTL -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- jQuery : CDN방식으로 추가함 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="images/${vo.image}" alt="..." />
                    </div>
                    <div class="col-md-6">
                        <div class="small mb-1">${vo.productCode}</div>
                        <h1 class="display-5 fw-bolder">${vo.productName}</h1>
                        <div class="fs-5 mb-5">
                            <span class="text-decoration-line-through">${vo.productPrice}원</span>
                            <span>${vo.salePrice}원</span>
                        </div>
                        <p class="lead">${vo.productDesc}</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1"
                                style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related items section-->

        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="list">
			


				<div class="col mb-5">
                <div class="card h-100">
                <!-- Sale badge-->
                <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                <!-- Product image-->
                <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                <!-- Product details-->
                <div class="card-body p-4">
                    <div class="text-center">
                        <!-- Product name-->
                        <h5 class="fw-bolder">${product.productName}</h5>
                        <!-- Product reviews-->
                        <div class="d-flex justify-content-center small text-warning mb-2">
                            <div class="bi-star-fill"></div>
                            <div class="bi-star-fill"></div>
                            <div class="bi-star-fill"></div>
                            <div class="bi-star-fill"></div>
                            <div class="bi-star-fill"></div>
                        </div>
                        <!-- Product price-->
                        <span class="text-muted text-decoration-line-through" id="productPrice">${product.productPrice}원</span>
                        ${product.salePrice}원
                    </div>
                </div>
                <!-- Product actions-->
                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                    <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                </div>
            </div>
        </div>
 
                </div>
            </div>
        </section>
        <script>

            fetch('relate.do')
                .then(resolve => resolve.json())
                .then(result => {
                	result.forEach(result => {
            			makeTr(result);
            		})
                })
                .catch(err => {
                    console.log(err);
                })
             
            function makeTr(result){
         		// tr : 댓글번호, 제목, 작성자, 작성일자
         		// tr : 댓글내용
         		let name = result.productName;
         		console.log(name);
         		let div = $('<div class="col mb-5" />').append( // 자식태그로 추가
         							
         							// text : innerText와 같은말 / html : innerHTML같은말
         							$('<div class="card h-100"/>'),
         							$('<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem"/>').text("sale"),
         							$('<img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />'),
         							$('<div class="card-body p-4"/>'),
         							$('<div class="text-center"/>'),
         							$('<h5 class="fw-bolder"/>').html(name);
         		
         						   )
         						   
         		console.log(div);
         		$("#list").append(div);
            }
            
        </script>