var bigScreen = false;

$(document).ready(function() {
	$('.sub-menu').each(function() {
		var mainMenu = $(this).attr('main-menu');
		$(this).hover(function() {
			var width = $(window).width();
			if (width > 991) {
				$('#' + mainMenu).addClass('sub-menu-bottom');
			}
		}, function() {
			var width = $(window).width();
			if (width > 991) {
				$('#' + mainMenu).removeClass('sub-menu-bottom');
			}
		});
	});
	if ($(window).width() > 991) {
		bigScreen = true;
	}
	$('.mall-menu li').each(function() {
		$(this).click(function(event) {
			event.stopPropagation();
			if (!bigScreen) {
				slideMenu($(this).find('.sub-menu'));
			}
		});
		var menu=$(this);
		$(this).hover(function() {
			if (bigScreen) {
				menu.find('.sub-menu').show();
			}
		}, function() {
			if (bigScreen) {
				menu.find('.sub-menu').hide();
			}
		});

	});
});

$(window).resize(function() {
	var width = $(window).width();
	bigScreen = false;
	if (width > 991) {
		bigScreen = true;
		$(".mall-menu").css('display', 'block');
	}
});

function showMenu() {
	slideMenu($(".mall-menu"));
}
function slideMenu(menu) {
	if (menu.css('display') === 'none') {
		menu.show("slow");
	} else {
		menu.hide("slow");
	}
}
function removeItem(index){
	$.ajax({url:"orderItem/itemAction!remove.action?index="+index,success:function(xhr){
		showCartDetail(xhr);
	}});
}
 
function showCartDetail(html)
{
      if(html!==""){
    	  $('#shopcart').empty();
    	  $(html).appendTo($('#shopcart')); 
		  $("#shopcart").trigger('mouseenter');
    	  setTimeout(function(){
    		  $("#shopcart").trigger('mouseleave');
    	  },1000);
       }
}

function shopCart(){
	document.location.href ="orderItem/itemAction!showCar.action";
}

function checkOut(){
	document.location.href ="goodsOrder/orderAction!pay.action";
}