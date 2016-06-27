<#import "layout/defaultTemplate.ftl" as layout>
    <@layout.myLayout>
            <h1>Borrower Details</h1>
        
		<br />

        <#if (model.person)??>
 
            <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
            <script type="text/javascript">

                function doReturnBook(personid, bookid) {
                
                	        $.post("/person/returnBook",
                	        	{
                	        		personId: personid,
                	        		bookId: bookid
                	        	},
                                function(response, status) {
                                
                                $("#forPageLoad").html(response);

                                }
                            );
                	
                }
 			</script>
 
 
            <div style="width: 700px">
                <table class="table">
                    <tr>
                        <th>Name</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                    </tr>
                    <tr id="${model.person.id}">
                        <td>${model.person.surname}, ${model.person.firstname}</td>
                        <td>${model.person.phone}</td>
                        <td>${model.person.email}</td>
                    </tr>
                </table>
            </div>

			<div id="forPageLoad">
				<#include "borrowedBooksForPerson.ftl"/>
			</div>


        <#else>
			<h2>Nobody has been specified</h2>
			<p>Select a person from the <a href="/people">people page</a></p>
        </#if>

    </@layout.myLayout>