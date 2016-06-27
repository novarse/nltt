<#import "layout/defaultTemplate.ftl" as layout>
    <@layout.myLayout>
        <h1>Book Details</h1>

        <br />

        <#if (model.book)??>
            <div style="width: 700px">
                <table class="table">
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>ISBN</th>
                        <th>Borrower</th>
                    </tr>
                    <tr>
                        <td>${model.book.title}</td>
                        <td>${model.book.author}</td>
                        <td>${model.book.isbn}</td>
                        <td>
			  			<#if (model.book.borrowerId > 0)>
                        <a href="/person/${model.book.borrowerId}">${model.book.borrowerName}</a>
                        </#if>
                        </td>
                    </tr>
                </table>
       
       			<#if (model.bookDetail)??>
       				<div>         
                    	<label for="detail">Book Detail</label>
      					<textarea readonly class="form-control" rows="6" id="detail">${model.bookDetail.detail}</textarea>
					</div>
				</#if>
            </div>
            <#else>
                <h2>No book has been specified</h2>
                <p>Select a book from the <a href="/books">book page</a></p>
        </#if>
    </@layout.myLayout>