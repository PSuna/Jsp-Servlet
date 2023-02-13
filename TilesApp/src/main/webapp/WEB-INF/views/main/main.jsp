<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- bxslider -->    
    
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>    
 
 
<div class="slider">
   <div><img width = "850px" src="images/pic1.PNG"></div>
   <div><img width = "850px" src="images/pic2.PNG"></div>
   <div><img width = "850px" src="images/pic3.PNG"></div>
</div>
 
<script>
$('.slider').bxSlider({
	auto: true,
	speed: 500,
	pause: 4000,
	mode:'fade',
	autoControls: true,
	pager:true,
});

</script>
