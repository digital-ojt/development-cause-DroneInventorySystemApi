// このファイルは、在庫管理システムのフロントエンドのJavaScriptファイルです。
// バックエンドのAPIを呼び出して在庫情報を取得し、表示します。


// デバッグモードのフラグ
const DEBUG_MODE = true;
const token = sessionStorage.getItem('jwtToken');
/**
 * デバッグログを出力する
 * @param  {...any} args - ログに出力する引数
 */
function debugLog(...args) {
    if (DEBUG_MODE) {
        console.log(...args);
    }
}

// onloadイベントで初期処理を実行
window.onload = function() {

    // カテゴリーのセレクトボックスを生成
    fetch('http://localhost:8080/api/categoryinfo/init', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`, // JWTトークンをヘッダーに追加
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        debugLog('カテゴリー情報:', data); // デバッグ用ログ
        populateCategorySelect(data);
    })
    .catch(error => console.error('Error:', error));
    
    // 名前のセレクトボックスを生成
    fetch('http://localhost:8080/api/stockinfo/category', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`, // JWTトークンをヘッダーに追加
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {  
        debugLog('名前情報:', data); // デバッグ用ログ
        populateNameSelect(data);
    })

   // 在庫情報を取得して一覧を表示
   fetchDataAndDisplay('http://localhost:8080/api/stockinfo/init');
};

// カテゴリーを取得してセレクトボックスを生成
function populateCategorySelect(categories) {
    const categorySelect = document.getElementById('categorySelect');
    categorySelect.innerHTML = '<option value="" selected>選択してください</option>'; 
    categories.forEach(category => {
        const option = document.createElement('option');
        option.value = category.categoryId;
        option.textContent = category.categoryName;
        categorySelect.appendChild(option);
    });
}

// 名前を取得してセレクトボックスを生成
function populateNameSelect(names) {
    const categorySelect = document.getElementById('nameSelect');
    nameSelect.innerHTML = '<option value="" selected>選択してください</option>'; 
    names.forEach(name => {
        const option = document.createElement('option');
        option.value = name.categoryId;
        option.textContent = name.name;
        nameSelect.appendChild(option);
    });
}

// カテゴリーが選択されたときに名前のリストを更新
categorySelect.addEventListener('change', function() {
    const categoryId = categorySelect.value;
    if (categoryId) {
        fetch(`http://localhost:8080/api/stockinfo/category?categoryId=${categoryId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`, // JWTトークンをヘッダーに追加
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            debugLog('名前情報(絞込):', data); // デバッグ用ログ
            populateNameSelect(data);
        })
        .catch(error => console.error('Error:', error));
    } else {
        // カテゴリーが選択されていない場合は名前のリストをクリア
        populateNameSelect([]);
    }
});

// 検索ボタンのクリックイベント
document.getElementById('searchButton').addEventListener('click', function() {
    const categoryId = document.getElementById('categorySelect').value;
    const nameSelect = document.getElementById('nameSelect');
    const name = nameSelect.value ? nameSelect.options[nameSelect.selectedIndex].text : '';
    const amount = document.getElementById('amountSelect').value;
    const amountCondition = document.getElementById('amountConditionSelect').value;

    let url = 'http://localhost:8080/api/stockinfo/search?';
    const params = [];

    if (categoryId) {
        params.push(`categoryId=${categoryId}`);
    }
    if (name) {
        params.push(`name=${encodeURIComponent(name)}`);
    }
    if (amount) {
        if (amountCondition === 'greater') {
            params.push(`amountMin=${amount}`);
        } else if (amountCondition === 'less') {
            params.push(`amountMax=${amount}`);
        }
    }

    if (params.length > 0) {
        url += params.join('&');
    } 

    fetchDataAndDisplay(url);
});

/**
 * データを取得して表示する
 * @param {string} url - データを取得するURL
 */
function fetchDataAndDisplay(url) {
    // リストを表示する要素
    const displayList = document.getElementById('display-list');

    // Fetch APIを使用してデータを取得
    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`, // JWTトークンをヘッダーに追加
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(data => {
        // デバッグ用ログ
        debugLog('検索URL:', url);
        debugLog('取得データ:', data);

        // データを取得して表示
        displayList.innerHTML = ''; // ローディングメッセージを削除

        // テーブルの作成
        const table = document.createElement('table');
        table.classList.add('table'); // CSSクラスを追加
        const thead = document.createElement('thead');
        const tbody = document.createElement('tbody');

        // テーブルヘッダーの作成
        const headerRow = document.createElement('tr');
        const headers = ['分類', '在庫名', '在庫数', 'センター', '説明'];
        headers.forEach(headerText => {
            const th = document.createElement('th');
            th.textContent = headerText;
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);

        // テーブルボディの作成
        data.forEach(stockInfo => {
            const row = document.createElement('tr');
            const cells = [
                stockInfo.categoryName,
                stockInfo.name,
                stockInfo.amount,
                stockInfo.centerName,
                stockInfo.description
            ];
            cells.forEach(cellText => {
                const td = document.createElement('td');
                td.textContent = cellText;
                row.appendChild(td);
            });
            tbody.appendChild(row);
        });

        table.appendChild(thead);
        table.appendChild(tbody);
        displayList.appendChild(table);
    })
    .catch(error => {
        // エラーハンドリング
        console.error('データの取得中にエラーが発生しました:', error);
        const displayList = document.getElementById('display-list');
        const errorMessage = 'データの取得中にエラーが発生しました。';
        displayList.innerHTML = `<span class="error-message">${errorMessage}</span>`;
    });
}