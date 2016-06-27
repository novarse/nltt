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
