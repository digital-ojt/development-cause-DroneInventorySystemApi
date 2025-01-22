// ログインボタン押下時イベント
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const adminId = document.getElementById('adminId').value;
    const password = document.getElementById('password').value;

    const loginData = { adminId, password };

    fetch('http://localhost:8080/api/admin/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
			console.log("Response Status:", response.status);  // ステータスコードを確認
            return response.json();
        } else {
            throw new Error('Login failed');
        }
    })
    .then(data => {
		if (data.token) {
			// jwtトークンをセッションストレージに保管
			sessionStorage.setItem('jwtToken', data.token);
		} else {
			throw new Error('No token received');
		}
		
		// ログイン成功メッセージ表示
        document.getElementById('message').textContent = 'Login successful!';
        document.getElementById('message').style.color = 'green';
        
        console.log("Login response data:", data);
        
        // Index.htmlへリダイレクト
        window.location.href = 'Index.html'
    })
    .catch(error => {
        document.getElementById('message').textContent = 'Login failed: ' + error.message;
        document.getElementById('message').style.color = 'red';
    });
});