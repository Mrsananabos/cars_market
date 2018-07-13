--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product
WHERE type_id = (
  SELECT type.id
  FROM type
  WHERE name = 'Сыр');

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product
WHERE name like '%МОРОЖЕННОЕ%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product 
where (product.expired_date < '20180811');

--4. Написать запрос, который вывод самый дорогой продукт.
SELECT * FROM product
WHERE price = (
  SELECT MAX(price)
  FROM product
);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(type_id) FROM product
WHERE type_id = (
  SELECT type.id
  FROM type
  WHERE type.name = 'Молоко'
);
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product
  WHERE type_id IN (
  SELECT type.id
  FROM type
  WHERE type.name = 'Сыр' OR type.name ='Молоко');
 
  
  
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
SELECT type_id
FROM product
WHERE product.quantity < 10
GROUP BY type_id;
 

--8. Вывести все продукты и их тип.
SELECT product.name, type.name FROM product, type
WHERE product.type_id = type.id