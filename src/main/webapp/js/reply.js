function toReply(repId){
        $('#reply'+repId).css("display","block");
        }
  function showReply(hideId){
        $('#hideId'+hideId).css("display","block");
        }
         function comm(commentNo,replyAmount)
		{ 
		if(replyAmount==15){
		  alert("Limited Replays");
		}
		else{
		    $('#form1').attr("action","reply/replyAction!addEntity.action?commentNo="+commentNo);
		    $('#form1').submit();
		    }
		}
         function cancel(index){
        	 $("#reply"+index).css("display","none");
        	 $('#estimate'+index).val("");
         }
         function toggleDetail(btn,index){
        	 var status=$("#hideId"+index).css("display");
        	 if(status==="none"){
        		 $("#hideId"+index).css("display","block");
        		 $(btn).text("Hide");
        	 }
        	 else if(status==="block"){       		 
        	 $("#hideId"+index).css("display","none");
    		 $(btn).text("Detail");
        	 }
        	 
         }