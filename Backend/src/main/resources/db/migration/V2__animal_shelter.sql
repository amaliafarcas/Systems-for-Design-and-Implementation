ALTER TABLE medical_record ADD COLUMN additional_information VARCHAR(1000);

ALTER TABLE animal DISABLE TRIGGER ALL;

DO $$
DECLARE
   v1 TEXT := 'The animal is known for its playful and curious personality. It loves to explore its surroundings, often poking its nose into new objects and sniffing around. The animal is highly social and thrives in the company of other animals, often engaging in playful activities with them. It is also known for its loyalty and affection towards its owners, often seeking their attention and affection in return. However, the animal can also be fiercely independent and stubborn at times, displaying a strong will and determination. Overall, the animals unique combination of playfulness, sociability, loyalty, and independence makes it a beloved companion to many.';

   v2 TEXT := 'This animal is known for its playfulness and energy. It loves to run, jump, and play games with its owners, and is always eager for a new adventure. Despite its energetic nature, this animal is also incredibly affectionate and loves to cuddle up with its owners for some quality snuggle time. With its cheerful disposition and friendly personality, its no wonder that this animal is a popular pet among families and individuals alike.';

   v3 TEXT := 'This animal is known for its quiet and reserved nature. It can be somewhat shy around new people, but once it gets to know someone, it becomes a loyal and devoted companion. Its very independent and doesnt require a lot of attention, but it does enjoy spending time with its owners, particularly in quiet and cozy environments. This animal is incredibly intelligent and is quick to learn new tricks and commands, making it an ideal pet for those who enjoy training and working with their animals.';


   v4 TEXT := 'This animal is known for its regal and dignified nature. It has a strong sense of independence and doesnt like to be bossed around or told what to do. It can be aloof at times, but once it forms a bond with its owners, it becomes incredibly affectionate and loyal. This animal is very intelligent and requires mental stimulation and challenges to keep it engaged and happy. Despite its independent streak, it craves human companionship and enjoys spending time with its owners, particularly in quiet and serene environments.';
BEGIN
   UPDATE medical_record
SET additional_information =
    CASE
        WHEN medical_record_id %4 = 1 THEN v1
        WHEN medical_record_id %4 = 2 THEN v2
        WHEN medical_record_id %4 = 3 THEN v3
        WHEN medical_record_id %4 = 0 THEN v4
    END;
END $$;

ALTER TABLE animal ENABLE TRIGGER ALL;