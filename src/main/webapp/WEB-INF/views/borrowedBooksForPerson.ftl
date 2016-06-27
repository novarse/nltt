<#if (model.borrowedBooksByPerson?has_content)>
    <div style="width: 700px">

        <h3>Books Borrowed</h3>

        <table class="table">
            <tr>
                <td colspan="4">
                    <table class="table-condensed">
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>ISBN</th>
                        </tr>
                        <#list model.borrowedBooksByPerson as book>
                            <tr>
                                <td><a href="/book/${book.id}">${book.title}</a></td>
                                <td>${book.author}</td>
                                <td>${book.isbn}</td>
                                <td><input type="type" value="Return" onclick="doReturnBook(${model.personId}, ${book.id});" style="width: 70px" class="toggle btn btn-sm btn-primary"></td>
                            </tr>
                        </#list>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </div>

    <#else>
        <h3>No books borrowed</h3>
</#if>


<#if (model.availableBooks?has_content)>
    <div style="width: 700px">
        <hr />

        <h3>Borrow Another Book</h3>

        <form action="borrowing" method="POST">
            <table>
                <tr>
                    <td>
                        <select name="bookId" class="form-control">
   							<option value="0" label="-Select a book to borrow-"/>
							<#list model.availableBooks?keys as value>
								<option value="${value}">${model.availableBooks[value].title}</option>
							</#list>
						</select>
                    </td>
                    <td>
                        <input type="hidden" name="personId" value="${model.personId}" />
                    </td>
                    <td>
                        <input type="submit" value="Borrow" class="toggle btn btn-default btn-primary" /></td>
                </tr>
            </table>
        </form>

    </div>

</#if>