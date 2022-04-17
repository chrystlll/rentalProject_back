-- Select all price actif
SELECT * FROM manage_rental.price pr where pr.common_status in ('ACTIF');

-- Select price actif for a specific contract id 
SELECT pr.*  FROM manage_rental.price pr, scheduled_payment sp, contract c  where pr.common_status in ('ACTIF') and c.id=1 group by c.id;
