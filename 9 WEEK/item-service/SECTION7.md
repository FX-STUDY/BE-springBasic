# 7. 스프링 MVC - 웹 페이지 만들기

**서비스 제공 흐름**
![S7-1.png](img%2FS7-1.png)


---


## 상품 도메인 개발 

*Item - 상품 객체*
```java
package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private long id;
    private String itemName;
    private Integer price; //null 가능
    private Integer quantity; //null 가능

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
```

*ItemRepository - 상품 저장소*
```java
package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
```

*ItemRepositoryTest - 상품 저장소 테스트*
```java
package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //give
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //give
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
        //give
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = item1.getId();

        //when
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
```

---


## 상품 서비스 HTML
- 상품 목록 HTML
- 상품 상세 HTML
- 상품 등록 폼 HTML
- 상품 수정 폼 HTML


---

## 상품 목록 - Thymeleaf

*BasicItemController*
```java
package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping()
    public String items(Model model) {
        List<Item> itmes = itemRepository.findAll();
        model.addAttribute("items",itmes);
        return "basic/items";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
```

- `@RequiredArgsController`
  - `final`이 붙은 멤버변수만 사용해 생성자들 자동으로 만듦


*items.html*
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <link th:href="@{/css/bootstrap.min.css}"
            href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container" style="max-width: 600px">
    <div class="py-5 text-center">
        <h2>상품 목록</h2>
    </div>

    <div class="row">
        <div class="col">
            <button class="btn btn-primary float-end"
                    onclick="location.href='addForm.html'"
                    th:onclick="|location.href='@{/basic/items/add}'|"
                    type="button">상품 등록</button>
        </div>
    </div>

    <hr class="my-4">
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="item : ${items}">
                <td><a href="item.html" th:href="@{/basic/items/{itemId}(itemId=${item.id})}" th:text="${item.id}">회원id</a></td>
                <td><a href="item.html" th:href="@{|/basic/items/${item.id}|}" th:text="${item.itemName}">상품명</a></td>
                <td th:text="${item.price}"></td>
                <td th:text="${item.quantity}"></td>
            </tr>
            </tbody>
        </table>
    </div>

</div> <!-- /container -->

</body>
</html>
```

### 타임리프 간단히 알아보기
**타임리프 사용 선언**<br>
`<html xmlns:th="http://www.thymeleaf.org">`

**속성 변경 - th:href**<br>
`th:href="@{css/bootstrap.min.css"`
- `href="xxx1"`을 `th:href="xxx2"`의 값으로 변경
- HTML을 그대로 볼 때는 `href`속성이 사용, 뷰 템플릿을 거치면 `th:href`의 값이 `href`로 대체돼 동적으로 변경 가능.
- 대부분의 HTML속성을 `th:xxx`로 변경 가능

**타임리프 핵심**
- 핵심 : `th:xxx`가 붙은 부분은 서버사이드에서 렌더링, 기존 것을 대체함.
`th:xxx`가 없으면 기존 html의 `xxx`속성이 그대로 사용됨.
- HTML을 파일로 직접 열었을 때, `th:xxx`가 있어도 웹 브라우저는 `th:`속성을 알지 못해 무시함.
- 따라서 HTML을 파일 보기를 유지하면서 템플릿 기능도 사용 가능.

**상품 등록 폼으로 이동**<br>
**속성 변경 - `th:onclick`**
- `onclick="location.href='addForm.html'"`
- `th:onclick="|location.href='@{/basic/items/add}'|"`

**리터럴 대체 - |...|**<br>
- 타임리프에서 문자와 표현식 등은  분리돼 있어 더해서 사용해야함.
  - ex) `<span th:text="'Welcome to our application, ' + ${user.name} + '!'">`
- 다음과 같이 리터럴 대체 문자를 사용하면, 더하기 없이 편리하게 사용 가능.
  - ex) `<span th:text="|Welcome to our application, ${user.name}!| " >`

- 결과를 다음과 같이 만들어야 하는데
  - `location.href='basic/items/add'`
- 그냥 사용하면 문자와 표현식을 따로 더해서 사용해야 하므로 복잡해짐.
  - `th:onclick="'location.href=' + '\'' + @{/basic/items/add} + '\''"`
- 리터럴 대체 문법을 사용하면 다음과 같이 편리하게 사용 가능
  - `th:onclick="|location.href='@{/basic/items/add}'|"`

**반복 출력 - th:each**
- `<tr th:each="item : ${items}>`
- 반복은 `th:each`를 사용. 이렇게 하면 모델에 포함된 `items`컬렉션 데이터가 `item`변수에 하나씩 포함되고,
반복문 안에 `item`변수를 사용할 수 있음.
- 컬렉션의 수 만큰 `<tr>...</tr>`이 하위 태그를 포함해서 생성됨.

**변수 표현식 - ${..}**
- `<td th:text="${item.price}">10000</td>`
- 내용의 값을 th:text 의 값으로 변경.
- 여기서는 10000을 ${item.price} 의 값으로 변경.

**URL 링크 표현식2 - @{...}**
- `th:href="@{/basic/items/{itemId}(itemId=${item.id})}"`
- 경로 변수 `({itemId})`뿐만 아니라 쿼리 파라미터도 생성.
- ex) `th:href="@{/basic/items/{itemId}(itemId=${item.id}, query='test')}"`
  - 생성 링크: http://localhost:8080/basic/items/1?query=test

**URL 링크 간단히**
- th:href="@{|/basic/items/${item.id}|}"
- 리터럴 대체 문법을 활용해서 간단히 사용할 수 있음.


> 타임리프는 순수 HTML 파일을 웹 브라우저에서 열어도 내용 확인이 가능, 서버를 통해 뷰 템플릿을 거치면
> 동적으로 변경된 결과를 확인할 수 있음. 하지만 JSP 파일은 웹 브러우저에서 그냥 열면 JSP 코드와 
> HTML 이 섞여 있어 정상적인 확인이 불가능. 오직 서버를 통해 JSP 를 열어야 함.<br>
> 이렇게 순수 **HTML 을 그대로 유지하면서 뷰 템플릿도 사용할 수 있는 타임리프의 특징을 네츄럴 템플릿**이라고 함.


---


## 상품 상세

*item method*
```java
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
```

`PathVariable`로 넘어온 상품ID로 상품을 조회하고, 모델에 담아둠. 그리고 뷰 템플릿을 호출.


*item.html*
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css"
          th:href="@{/css/bootstrap.min.css}" rel="stylesheet">
    <style>
        .container {
            max-width: 560px;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="py-5 text-center">
        <h2>상품 상세</h2>
    </div>

    <div>
        <label for="itemId">상품 ID</label>
        <input type="text" id="itemId" name="itemId" class="form-control" value="1" th:value="${item.id}" readonly>
    </div>
    <div>
        <label for="itemName">상품명</label>
        <input type="text" id="itemName" name="itemName" class="form-control" value="상품A"  th:value="${item.itemName}" readonly>
    </div>
    <div>
        <label for="price">가격</label>
        <input type="text" id="price" name="price" class="form-control" value="10000"  th:value="${item.price}" readonly>
    </div>
    <div>
        <label for="quantity">수량</label>
        <input type="text" id="quantity" name="quantity" class="form-control" value="10"  th:value="${item.quantity}" readonly>
    </div>

    <hr class="my-4">

    <div class="row">
        <div class="col">
            <button class="w-100 btn btn-primary btn-lg"
                    onclick="location.href='editForm.html'"
                    th:onclick="|location.href='@{/basic/items/{itemId}/edit(itemId=${item.id})}'|"
                    type="button">상품 수정</button>
        </div>
        <div class="col">
            <button class="w-100 btn btn-secondary btn-lg"
                    onclick="location.href='items.html'"
                    th:onclick="|location.href='@{/basic/items}'|"
                    type="button">목록으로</button>
        </div>
    </div>

</div> <!-- /container -->
</body>
</html>
```

**속성 변경 - th:value**<br>
`th:value="${item.id}"`
- 모델이 있는 item 정보를 획득하고 프로퍼티 접근법으로 출력한다. `(item.getId())`
- `value` 속성을 `th:value` 속성으로 변경.

**상품 수정 링크**
- `th:onclick="|location.href='@{/basic/items/{itemId}/edit(itemId=${item.id})}'|"`

**목록으로 링크**
- `th:onclick="|location.href='@{/basic/items}'|"`


## 상품 등록 폼

*addForm method*
```java
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }
```

*addForm.html - 상품 등록 폼 뷰*
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css"
          th:href="@{/css/bootstrap.min.css}"
          rel="stylesheet">
    <style>
        .container {
            max-width: 560px;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="py-5 text-center">
        <h2>상품 등록 폼</h2>
    </div>

    <h4 class="mb-3">상품 입력</h4>

    <form action="item.html" th:action="@{/basic/items/add}" method="post">
        <div>
            <label for="itemName">상품명</label>
            <input type="text" id="itemName" name="itemName" class="form-control" placeholder="이름을 입력하세요">
        </div>
        <div>
            <label for="price">가격</label>
            <input type="text" id="price" name="price" class="form-control" placeholder="가격을 입력하세요">
        </div>
        <div>
            <label for="quantity">수량</label>
            <input type="text" id="quantity" name="quantity" class="form-control" placeholder="수량을 입력하세요">
        </div>

        <hr class="my-4">

        <div class="row">
            <div class="col">
                <button class="w-100 btn btn-primary btn-lg" type="submit">상품 등록</button>
            </div>
            <div class="col">
                <button class="w-100 btn btn-secondary btn-lg"
                        onclick="location.href='items.html'"
                        th:onclick="|location.href='@{/basic/items}'|"
                        type="button">취소</button>
            </div>
        </div>

    </form>

</div> <!-- /container -->
</body>
</html>
```

**속성 변경 - th:action**
- `th:action`
- HTML form에서 `action` 에 값이 없으면 현재 URL에 데이터를 전송한다.
- 상품 등록 폼의 URL과 실제 상품 등록을 처리하는 URL을 똑같이 맞추고 HTTP 메서드로 두 기능을 구분한다.
  - 상품 등록 폼: GET `/basic/items/add`
  - 상품 등록 처리: POST `/basic/items/add`
-이렇게 하면 하나의 URL로 등록 폼과, 등록 처리를 깔끔하게 처리할 수 있다.
 
**취소**
- 취소시 상품 목록으로 이동한다.
- `th:onclick="|location.href='@{/basic/items}'|"`


## 상품 등록 처리 - @ModelAttribute

상품 등록 폼은 다음 방식으로 서버에 데이터를 전달.
- POST - HTML Form
  - `content-type: application/x-www-form-urlencoded`
  - 메시지 바디에 쿼리 파리미터 형식으로 전달 `itemName=itemA&price=10000&quantity=10`
  - 예) 회원 가입, 상품 주문, HTML Form 사용

요청 파라미터 형식을 처리하기 위해 `@RequestParam`을 사용해야 함.



### 상품 등록 처리 - @RequestParam

*addItemV1 method*
```java
@PostMapping("/add")
public String addItemV1(@RequestParam String itemName, 
                        @RequestParam int price, 
                        @RequestParam Integer quantity, 
                        Model model) {
    Item item = new Item();
    item.setItemName(itemName);
    item.setPrice(price);
    item.setQuantity(quantity);
    itemRepository.save(item);
    model.addAttribute("item", item);
    return "basic/item";
}
```
- @RequestParam String itemName` : itemName 요청 파라미터 데이터를 해당 변수에 받음.
- `Item` 객체를 생성하고 `itemRepository` 를 통해서 저장.
- 저장된 item 을 모델에 담아서 뷰에 전달.


### 상품 등록 처리 - @ModelAttribute

*addItemV2 method*
```java
@PostMapping("/add")
public String addItemV2(@ModelAttribute("item") Item item, Model model) {
    itemRepository.save(item);
    //model.addAttribute("item", item); //자동 추가, 생략 가능
    return "basic/item";
}
```

**@ModelAttribute - 요청 파라미터 처리**
- `@ModelAttribute` 는 Item 객체를 생성, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력.

**@ModelAttribute - Model 추가**
- `@ModelAttribute` 는 중요한 한가지 기능이 더 있는데, 바로 모델(Model)에 `@ModelAttribute`로 지정한 객체를 자동으로 넣어줌.
코드를 보면 model.addAttribute("item", item) 가 주석처리 되어 있어도 잘 동작하는 것을 확인 가능

모델에 데이터를 담을 때는 이름이 필요. 이름은 `@ModelAttribute`에 지정한 `name(value)`속성을 사용한다.
만약 다음과 같이 `@ModelAttribute`의 이름을 다르게 지정하면 다른 이름으로 모델에 포함.

EX) <br>
- @ModelAttribute("hello") Item item 이름을 hello 로 지정
- model.addAttribute("hello", item); 모델에 hello 이름으로 저장


*addItemV3 - 상품 등록 처리 - Model Attribute 이름 생략*
```java
@PostMapping("/add")
public String addItemV3(@ModelAttribute Item item) {
    itemRepository.save(item);  
    return "basic/item";
}
```
`@ModelAttribute`의 이름 생략 가능

**주의**<br>
`@ModelAttribute` 의 이름을 생략하면 모델에 저장될 때 클래스명을 사용.
이때 클래스의 첫글자만 소문자로 변경해서 등록.

EX)<br>
- `@ModelAttribute`클래스명 -> 모델에 자동 추가되는 이름
  - `Item -> item`
  - `HelloWorld -> helloWorld`


*addItemV4 - 상품 등록 처리 - ModelAttribute 전체 생략*
```java
@PostMapping("/add")
public String addItemV4(Item item) {
    itemRepository.save(item);
    return "basic/item";
}
```
`@ModelAttribute`자체도 생략 가능. 대상 객체는 모델에 자동 등록. 나머지 사항은 기존과 동일.



## 상품 수정

*editForm method - 상품 수정 폼 컨트롤러*
```java
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }
```


*editForm.html - 상품 수정 폼 뷰*
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css"
          th:href="@{/css/bootstrap.min.css}"
          rel="stylesheet">
    <style>
        .container {
            max-width: 560px;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="py-5 text-center">
        <h2>상품 수정 폼</h2>
    </div>

    <form action="item.html" th:action method="post">
        <div>
            <label for="id">상품 ID</label>
            <input type="text" id="id" name="id" class="form-control" value="1" th:value="${item.id}" readonly>
        </div>
        <div>
            <label for="itemName">상품명</label>
            <input type="text" id="itemName" name="itemName" class="form-control" th:value="${item.itemName}" value="상품A">
        </div>
        <div>
            <label for="price">가격</label>
            <input type="text" id="price" name="price" class="form-control" value="10000" th:value="${item.price}">
        </div>
        <div>
            <label for="quantity">수량</label>
            <input type="text" id="quantity" name="quantity" class="form-control" th:value="${item.quantity}" value="10">
        </div>

        <hr class="my-4">

        <div class="row">
            <div class="col">
                <button class="w-100 btn btn-primary btn-lg" type="submit">저장</button>
            </div>
            <div class="col">
                <button class="w-100 btn btn-secondary btn-lg"
                        onclick="location.href='item.html'"
                        th:onclick="|location.href='@{/basic/items/{itemId}(itemId=${item.id})}'|"
                        type="button">취소</button>
            </div>
        </div>

    </form>

</div> <!-- /container -->
</body>
</html>
```


### 상품 수정 개발
```java
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }
```

- GET `/items/{itemId}/edit` : 상품 수정 폼
- POST `/items/{itemId}/edit` : 상품 수정 처리


**리다이렉트**<br>
상품 수정은 마지막에 뷰 템플릿을 호출하는 대신에 상품 상세 화면으로 이동하도록 리다이렉트 호출
- 스프링은 `redirect:/....`으로 편리하게 리다이레그를 지원.
- `redirect:/basic/items/{itemId}`
  - 컨트롤러에 매핑된 `@PathVariable`의 값은 `redirect` 에도 사용 가능.
  - `redirect:/basic/items/{itemId}` -> `{itemId}`는 
  `@PathVariable("itemId") Long itemId` 의 값을 그대로 사용함.



## PRG Post/Redirect/Get
현재 코드에는 문제가 있다. 상품 등록 후 새로고침을 할 때 상품이 중복해서 등록된다.

**POST 등록 후 새로고침**
![S7-2.png](img%2FS7-2.png)

상품 등록 폼에서 데이터 입력, 저장을 선택하면 `POST /add` + 상품 데이터로 서버로 전송 <br>
이 상태에서 새로고침을 하면 마지막에 전송한 `POST /add` + 상품 데이터로 다시 전송하게 된다.<br>
그래서 내용은 같고, ID만 다른 상품 데이터가 쌓인다.



**POST, Redirect, GET**
![S7-3.png](img%2FS7-3.png)

새로 고침 문제를 해결하려면 상품 저장 후 뷰 템플릿으로 이동하는 것이 아니라, 상품 상세 화면으로 리다이렉트 호출하면 된다.<br>
웹 브라우저는 리다이렉트의 영향으로 사움 저장 후 실제 상품 상세 화면으로 다시 이동한다.
따라서 마지막에 호출한 내용이 상품 상세 화면인 `GET /items/{id}`가 된다.

*save method 수정*
```java
    @PostMapping("/add")
    public String save(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        //model.addAttribute("item", item); //자동 추가, 생략 가능
//        return "basic/item";
        return "redirect:/basic/items/" + item.getId();
    }
```

이런 문제 해결 방식을 `PRG Post/Redirect/Get`라 한다.


## RedirectAttributes
상품이 저장이 잘 됐다면 "저장 완료"라는 메시지를 보여주게 수정한다.

*save method 수정*
```java
    @PostMapping("/add")
    public String save(@ModelAttribute("item") Item item, Model model, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능
//        return "basic/item";
        redirectAttributes.addAttribute("savedItem", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{savedItem}";
    }
```
실행 시 리다이렉트 결과<br>
`http://localhost:8080/basic/items/3?status=true`

**RedirectAttributes**<br>
`RedirectAttributes`를 사용하면 URL인코딩도 해주고, `pathVariable`, 쿼리 파라미터도 처리함.
- `redirect:/basic/items/{itemId}`
- pathVariable 바인딩: `{itemId}`
- 나머지는 쿼리 파라미터로 처리: `?status=true`


*item.html - 뷰 템플릿 메시지 추가*
```html
    <div class="py-5 text-center">
        <h2>상품 상세</h2>
    </div>

    <h2 th:if="${param.status}" th:text="'저장 완료'"></h2> 

    <div>
```

- `th:if` : 해당 조건이 참이면 실행
- `${param.status}` : 타임리프에서 쿼리 파라미터를 편리하게 조회하는 기능
  - 원래는 컨트롤러에서 모델에 직접 담고 값을 꺼내야 함.
  쿼리 파라미터는 자주 사용해서 타임리프에서 직접 지원.





---