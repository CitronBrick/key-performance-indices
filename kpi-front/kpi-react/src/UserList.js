import React from 'react';


function UserList(props) {

	var trList = props.users.map((u,i)=> {
		return <tr><td>{u.name}</td><td>{u.isAdmin}</td>;
	});


	return <table class="userList">
		<thead>
			<tr><th>Email</th><th>Admin</th></tr>
		</thead>
		<tbody>

		</tbody>
	</table>;
}