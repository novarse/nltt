<#import "layout/defaultTemplate.ftl" as layout>
    <@layout.myLayout>
            <h1>People</h1>
        
		<br />

        <#if (model.people)??>
            <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
            <script type="text/javascript">
                var hidebooks = 'Hide Books';
                var showbooks = 'Show Books';

                function doShowHideBooks(personid) {
                    var buttonid = "#toggle" + personid;
                    var borrowedid = "#borrowed" + personid;
                    var borrowedtr = "#borrowedtr" + personid;

                    if ($(buttonid).attr('value') == showbooks) {

                            $.ajax({
                                type: "GET",
                                url: "/people/show/" + personid,
                                success: function(response) {

                                    // clear other book lists
                                    $(".shown").each( function() {
										hideBookSection(this);
                                    });

                                    // present current list
                                    $(borrowedid).html(response);
                                    
                                    // add shown class to list section so that it makes it easier to locate and hide it later
                                    $(borrowedtr).addClass("shown");
                                    $(borrowedtr).show();

                                    // toggle button text
                                    toggleButtons(buttonid);

                                }
                            });

                    } else {
                        // hiding books
                        // change button text to "Show Books"
                        $(buttonid).attr('value', showbooks);

                        // hide books
                        hideBookSection(borrowedtr);
                    }

                }

                function doHideBooks(personid) {
                    $.ajax({
                        type: "GET",
                        url: "/people/hide/" + personid,
                        success: function(response) {
                            $(borrowedid).html(response);
                        }
                    });
                }

				function hideBookSection(booksection) {
					$(booksection).css("shown","");
                    $(booksection).hide();
				}
				 
                function toggleButtons(buttonid) {
                    $('.toggle').each(function() {
                        if ($(this).is(buttonid)) {
                            $(this).attr('value', hidebooks);
                        } else {
                            // found other button. If text is "Hide Books" then change its text and hide its books.
                            if ($(this).attr('value') == hidebooks) {
                                $(this).attr('value', showbooks);

                            }
                        }
                    });
                }
            </script>

            <div style="width: 700px">
                <table class="table">
                    <tr>
                        <th>Name</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                        <th>Borrowed Books</th>
                    </tr>
                    <#list model.people as person>
                        <tr>
                            <td><a href="/person/${person.id}">${person.surname}, ${person.firstname}</a></td>
                            <td>${person.phone}</td>
                            <td>${person.email}</td>
                            <td>
                            	<#if (person.booksBorrowedTot != 0)>
                            	<input id="toggle${person.id}" type="button" value="Show Books" onclick="doShowHideBooks(${person.id});" style="width: 100px" class="toggle btn btn-default btn-sm btn-primary">
                            	</#if>
                            </td>
                        </tr>
                        <tr id="borrowedtr${person.id}" style="display: none">
                            <td colspan="4">
                                <div id="borrowed${person.id}"></div>
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>

        </#if>
    </@layout.myLayout>