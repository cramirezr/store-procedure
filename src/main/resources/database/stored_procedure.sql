-- Procedure to generate user request for forgotten password
create or replace procedure forgot_passw_req(
	req_user_id int,
	inout result int default 0)
language plpgsql
as $$
DECLARE
user_request_row user_forgot_passw_reqs%rowtype default null;
new_hash varchar;
BEGIN

select md5(concat(id, username, now())) into new_hash
from user_customer where id = req_user_id;

--Need to get latest possible record
select * into user_request_row
from user_forgot_passw_reqs
where user_id = req_user_id
order by created_dtm desc LIMIT 1;

--Entries found for user
IF found THEN
	IF now() < user_request_row.expiration THEN --not expired Do nothing return 1
		result = 1;
	ELSE -- Expired: update last record
		update user_forgot_passw_reqs
		set hash = new_hash, created_dtm = now(), expiration = now() + interval '45 seconds'
		where id = user_request_row.id;
		commit;
		result = 1;

	END IF;
ELSE --No Entries - insert
	insert into user_forgot_passw_reqs (user_id, hash, created_dtm, expiration)
	values (req_user_id, new_hash, now(), now() + interval '45 seconds');
	commit;
	result = 1;

END IF;
END
$$;
