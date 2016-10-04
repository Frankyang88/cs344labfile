select distinct op.period_id, op.employee_id from
(select distinct t.period_id, t.employee_id, 'PT_AC_ACC_LEAVE' as type from period p join txn t on p.id = t.period_id where state = 1) op
left join txn t on op.period_id = t.period_id and op.employee_id = t.employee_id and op.type = t.type
where t.type is null
