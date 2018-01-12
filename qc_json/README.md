> javac -cp gson-2.6.2.jar Solution.java 
> cat ~/Downloads/test_cases_a3qmjka3jjm/input002.txt | java -cp gson-2.6.2.jar:. Solution

<div aria-label="Question Content" class="challenge-text hrx-version ck_table-wrap fadeinContent" style="min-height:100px;"><p><em>Query By Example</em>&nbsp;allows a client to specify a
structure in which is used as the&nbsp;<em>example</em>, and all entities which&nbsp;<em>match</em>&nbsp;are returned. Implement a basic storage mechanism which allows a user to store
documents in JSON format. The structure of the document may be otherwise arbitrary. The program accepts a command through <code>stdin</code>, followed by a space, followed by
a&nbsp;document, followed by a newline. There are three allowed commands.</p>

<p>&nbsp;</p>

<ul>
	<li><em>add</em>, store the given document</li>
	<li><em>get</em>, find all documents which have the same properties and property values as the given document, and emit them to&nbsp;<code>stdout</code><em>.</em></li>
	<li><em>delete,</em>&nbsp;remove all documents which have the same properties and property values as the given document<em>.</em></li>
</ul>

<p>&nbsp;</p>

<p>The commands are all lowercase.</p>

<p>&nbsp;</p>

<p>Given the input:</p>

<pre>add {"id":1,"last":"Doe","first":"John","location":{"city":"Oakland","state":"CA","postalCode":"94607"},"active":true}
add {"id":2,"last":"Doe","first":"Jane","location":{"city":"San Francisco","state":"CA","postalCode":"94105"},"active":true}
add {"id":3,"last":"Black","first":"Jim","location":{"city":"Spokane","state":"WA","postalCode":"99207"},"active":true}
add {"id":4,"last":"Frost","first":"Jack","location":{"city":"Seattle","state":"WA","postalCode":"98204"},"active":false}
get {"location":{"state":"WA"},"active":true}
get {"id":1}
</pre>

<p>Emit:</p>

<pre>{"id":3,"last":"Black","first":"Jim","location":{"city":"Spokane","state":"WA","postalCode":"99207"},"active":true}
{"id":1,"last":"Doe","first":"John","location":{"city":"Oakland","state":"CA","postalCode":"94607"},"active":true}
</pre>

<p><strong>Note</strong> <em>that the documents must be output in the <strong>exact</strong> <strong>format</strong> as they were input</em>. If multiple documents are matched,
the&nbsp;documents must be emitted in the <strong><em>order they were created</em></strong>.&nbsp; The input may be rather complex and that the number of documents or the number of queries
may be quite large.</p>

<p>&nbsp;</p>

<p><strong>Handling Lists</strong></p>

<p>Support lists as "in list" expressions, for example, given the following input:</p>

<p>&nbsp;</p>

<pre>add {"type":"list","list":[1,2,3,4]}
add {"type":"list","list":[2,3,4,5]}
add {"type":"list","list":[3,4,5,6]}
add {"type":"list","list":[4,5,6,7]}
add {"type":"list","list":[5,6,7,8]}
add {"type":"list","list":[6,7,8,9]}
get {"type":"list","list":[1]}
get {"type":"list","list":[3,4]}</pre>

<p>Yield:</p>

<pre>{"type":"list","list":[1,2,3,4]}
{"type":"list","list":[1,2,3,4]}
{"type":"list","list":[2,3,4,5]}
{"type":"list","list":[3,4,5,6]}
</pre>

<p><strong>It should be noted</strong> that it is not expected that the author write a JSON implementation. <em>Most</em> of the programming environments within HackerRank include a JSON
library. To determine which one and how to use it, please refer to the <a href="https://www.hackerrank.com/environment">Hacker Rank Environment Matrix</a>.</p>
</div>
