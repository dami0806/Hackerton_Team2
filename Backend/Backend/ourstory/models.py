from django.db import models
from datetime import date

class Calendar(models.Model):
    id = models.AutoField(primary_key=True) # 자동으로 올라감
    # user_id = models.ForeignKey(User, on_delete=models.CASCADE, db_column='user_id')
    start_date = models.DateField(blank=False, default=date.today)
    end_date = models.DateField(blank=False, default=date.today)
    title = models.CharField(max_length=25, null=True)  
    text = models.CharField(max_length=255, null=True)
    image = models.CharField(max_length=255, null=True)

    class Meta:
        db_table = 'calendars'

# class User(AbstractUser):
#     id = models.IntegerField(primary_key=True)
#     nickname = models.CharField(max_length=10)
#     email = models.EmailField(blank=False, unique=True)
#     birth = models.DateField(blank=False)

#     USERNAME_FIELD = 'email'
#     REQUIRED_FIELDS = ['username', 'birth']

#     class Meta:
#         db_table = 'user'