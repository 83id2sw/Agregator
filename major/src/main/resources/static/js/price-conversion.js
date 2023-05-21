window.onload = function () {
    let prices = document.getElementsByClassName('item-price');
    let categories = document.getElementsByClassName('category-name');
    let sex = document.getElementsByClassName('SEX');
    let translateMap = 
    {
        "men": "Мужчинам",
        "women": "Женщинам",
        "pants": "Штаны",
        "shirt": "Рубашки",
        "hat": "Головные уборы",
        "shoes": "Обувь",
        "socks": "Носки"
    } 
    console.log(translateMap['men'])
    for (let i = 0; i < prices.length; i++) {
        prices[i].innerHTML = prices[i].textContent + ' ≈ ₽' + Math.round(prices[i].textContent.slice(1) * 79.98)
    }
    for (let i = 0; i < categories.length; i++) {
        categories[i].innerHTML = translateMap[(categories[i].textContent.toLowerCase())]
    }
    for (let i = 0; i < sex.length; i++) {
        sex[i].innerHTML = translateMap[(sex[i].textContent.toLowerCase())]
    }
}