<#import "layout/defaultTemplate.ftl" as layout>
<@layout.myLayout>
  <h1>Books</h1>

		<br />

 <div style="width: 700px">
   <table class="table">
  	<tr>
  		<th>Title</th>  
  		<th>Author</th> 
  		<th>ISBN</th>
  		<th>Borrower</th>
  	</tr>
  	    <#list model["books"] as book>
  	<tr>
  		<td><a href="/book/${book.id}">${book.title}</a></td> 
  		<td>${book.author}</td> 
  		<td>${book.isbn}</td> 
  		<td>
  			<#if (book.borrowerId > 0)>
  			<a href="/person/${book.borrowerId}">${book.borrowerName}</a>
  			</#if>
  		</td>
  	</tr>
    </#list>
  </table>
 </div>
</@layout.myLayout>

